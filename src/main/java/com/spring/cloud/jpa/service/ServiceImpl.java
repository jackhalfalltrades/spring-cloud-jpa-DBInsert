package com.spring.cloud.jpa.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.cloud.jpa.exception.InternalServerErrorException;
import com.spring.cloud.jpa.exception.MAATRuntimeException;
import com.spring.cloud.jpa.exception.ResourceNotFoundException;
import com.spring.cloud.jpa.model.*;
import com.spring.cloud.jpa.repository.HostRepository;
import com.spring.cloud.jpa.repository.InfrastructureMappingRepository;
import com.spring.cloud.jpa.repository.JobRepository;
import com.spring.cloud.jpa.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@org.springframework.stereotype.Service("jobCreateService")
public class ServiceImpl implements Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);
    private static final String JOB_STATUS = "New";
    private JobRepository jobRepository;
    private InfrastructureMappingRepository infrastructureMappingRepository;
    private TaskRepository taskRepository;
    private HostRepository hostRepository;
    private String param = "";

    @Autowired
    public ServiceImpl(JobRepository jobRepository, InfrastructureMappingRepository infrastructureMappingRepository, TaskRepository taskRepository, HostRepository hostRepository) {
        this.jobRepository = jobRepository;
        this.infrastructureMappingRepository = infrastructureMappingRepository;
        this.taskRepository = taskRepository;
        this.hostRepository = hostRepository;
    }

    @HystrixCommand(groupKey = "jobCreateCommand", commandKey = "jobCreateCommand", threadPoolKey = "jobCreateCommand")
    @Override
    public Response jobCreate(Payload payload) {
        byte[] jobParam;
        List<String> artifactList;
        List<InfrastructuremMapping> mappingRepositoryList;
        String taskName;
        try {
            LOGGER.info("jobCreateCommand(): -> New job build is in process!!!");
            mappingRepositoryList = infrastructureMappingRepository.findAllById(payload.getMappingId());
            if (mappingRepositoryList.isEmpty())
                throw new ResourceNotFoundException("No mapping found for the mapping ID's : " + payload.getMappingId(), new Object[]{payload.getMappingId(), payload});
            LOGGER.debug("jobCreateCommand(): mapping repositories -> {}", mappingRepositoryList);
            Optional<Task> task = taskRepository.findById(payload.getTaskOptionsModel().get(0));
            if (task.isPresent()) {
                taskName = task.get().getTaskName();
            } else {
                throw new MAATRuntimeException("Empty Task list for task-ID: " + payload.getTaskOptionsModel(), new Object[]{payload.getArtifactOptionsModel(), payload});
            }
            if (taskName.equalsIgnoreCase("Deploy")) {
                artifactList = payload.getArtifactOptionsModel();
                if (artifactList.isEmpty())
                    throw new MAATRuntimeException("No Artifact found in payload:  " + payload.getArtifactOptionsModel(), new Object[]{payload.getArtifactOptionsModel(), payload});
                jobParam = getJobParam(mappingRepositoryList, artifactList);
            } else {
                jobParam = getJobParam(mappingRepositoryList);
            }
            Job job = getJob(payload, taskName, jobParam);
            LOGGER.info("jobCreateCommand(): build job with task type -> {}", job.getJobType());
            Job savedJob = jobRepository.save(job);
            LOGGER.debug("jobCreateCommand(): job {} is built, job Details -> {}", savedJob.getId(), savedJob);
            return Response.builder().jobID(savedJob.getId()).build();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), new Object[]{e.getParams()});
        } catch (InternalServerErrorException e) {
            throw new InternalServerErrorException(e.getMessage(), new Object[]{e.getClass().getName(), e.getParams()});
        } catch (NoSuchElementException | NullPointerException e) {
            throw new InternalServerErrorException(e.getMessage(), new Object[]{e.getClass().getName(), e});
        } catch (Exception e) {
            throw new MAATRuntimeException(e.getLocalizedMessage(), new Object[]{e.getCause(), e.getClass().getName(), e.getMessage()});
        }
    }

    private Job getJob(Payload payload, String jobType, byte[] jobParams) {
        return Job.builder()
                .application(payload.getApplicationOptionsModel().get(0))
                .jobStatus(JOB_STATUS)
                .createdTimeStamp(getDate())
                .createdBy(payload.getUserName())
                .requestType(payload.getRequestTypeOptionsModel().get(0))
                .requestNumber(payload.getTicketNumber())
                .jobType(jobType)
                .isScheduled(payload.getIsScheduled() == null ? "No" : payload.getIsScheduled())
                .param(jobParams)
                .build();
    }

    private Date getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        return new Date();
    }

    private byte[] getJobParam(List<InfrastructuremMapping> infrastructureMappingList) {
        param = "";
        infrastructureMappingList.forEach(e -> param = param + "'" + e.getInstancesName() + "':{'home':'" + e.getInstallationHomePath() + "','cli':'" + e.getInstallationHomePath() + e.getCliPath() + "','host':'" +
                e.getHOSTId() + "','iName':'" + e.getInstancesName() + "','lFPath':'" + e.getLogFilePath() + e.getLogFileName() + "','mPort':'" + e.getManagementCLIPort() +
                "','sPath':'','dPath':'','artifact':''}}}");
        String jobParam = ("{'args':{" + param).replace("\\", "");
        LOGGER.debug("jobCreateCommand(): job build params -> {}", jobParam);
        return jobParam.getBytes();
    }

    private byte[] getJobParam(List<InfrastructuremMapping> infrastructureMappingList, List<String> artifactList) {
        infrastructureMappingList.stream().forEach(e -> {
            String[] artifacts = e.getArtifactName().split("-");
            String[] artifactSrcPathMap = e.getArtifactSourcePath().split(",");
            String artifact = "";
            String artifactSourcePath = "";
            param = "";
            for (String anArtifactList : artifactList) {
                for (String artifact1 : artifacts) {
                    if (artifact1.equalsIgnoreCase(anArtifactList))
                        artifact = artifact1;
                }
            }

            for (String anArtifactSrcPathMap : artifactSrcPathMap) {
                if (artifact.equalsIgnoreCase(anArtifactSrcPathMap.substring(0, anArtifactSrcPathMap.indexOf(":"))))
                    artifactSourcePath = anArtifactSrcPathMap.substring(anArtifactSrcPathMap.indexOf(":") + 1, anArtifactSrcPathMap.length());
            }

            param = param + "'" + e.getInstancesName() + "':{'home':'" + e.getInstallationHomePath() + "','cli':'" + e.getInstallationHomePath() + e.getCliPath() + "','host':'" +
                    hostRepository.findById(e.getHOSTId()).get().getHostName() + "','iName':'" + e.getInstancesName() + "','lFPath':'" + e.getLogFilePath() + e.getLogFileName() + "','mPort':'" + e.getManagementCLIPort() +
                    "','sPath':'" + artifactSourcePath + "','dPath':'" + e.getDeploymentPath().substring(e.getDeploymentPath().indexOf(':') + 1, e.getDeploymentPath().length()) + "','artifact':'" + artifact + "'}}}";
        });
        String jobParam = ("{'args':{" + param).replace("\\", "");
        LOGGER.debug("jobCreateCommand(): job build params -> {}", jobParam);
        return jobParam.getBytes();
    }

}
