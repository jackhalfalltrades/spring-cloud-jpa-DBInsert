package com.maat.bestbuy.integration.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "INFRASTRUCTURE_MAPPING")
public class InfrastructuremMapping {

    @Id
    @Column(name = "ID")
    private String mappingId;
    @Column(name = "JAVA_HOME_PATH")
    private String javaHomePath;
    @Column(name = "INSTALLATION_HOME_PATH")
    private String installationHomePath;
    @Column(name = "MASTER_SLAVE")
    private String masterSlave;
    @Column(name = "NUMBER_OF_PROFILES")
    private String numberofProfiles;
    @Column(name = "PROFILE_NAME")
    private String profileName;
    @Column(name = "NUMBER_OF_DOMAINS")
    private String numberofDomains;
    @Column(name = "DOMAIN_NAMES")
    private String domainNames;
    @Column(name = "NUMBER_OF_CLUSTERS")
    private String numberofClusters;
    @Column(name = "CLUSTER_NAMES")
    private String clusterNames;
    @Column(name = "NUMBER_OF_INSTANCES")
    private String numberofInstances;
    @Column(name = "INSTANCES_NAME")
    private String instancesName;
    @Column(name = "STARTUP_SCRIPT_NAME")
    private String startupScriptName;
    @Column(name = "STARTUP_SCRIPT_PATH")
    private String startupScriptPath;
    @Column(name = "STOP_SCRIPT_NAME")
    private String stopScriptName;
    @Column(name = "STOP_SCRIPT_PATH")
    private String stopScriptPath;
    @Column(name = "CERTIFICATE_NAME")
    private String certificateName;
    @Column(name = "CERTIFICATE_SERIAL_NUMBER")
    private String certificateSerialNumber;
    @Column(name = "CERTIFICATE_PATH")
    private String certificatePath;
    @Column(name = "CERTIFICATE_CREATED_DATE")
    private String certificateCreatedName;
    @Column(name = "CERTIFICATE_EXPIRATION_DATE")
    private String certificateExpirationDate;
    @Column(name = "CERTIFICATE_CN_SAN_ENTRIES")
    private String certificateCNSANEntries;
    @Column(name = "DEPLOYMENT_PATH_DEST")
    private String deploymentPath;
    @Column(name = "DEPLOYMENT_TYPE")
    private String deploymentType;
    @Column(name = "ARTIFACT_NAME")
    private String artifactName;
    @Column(name = "LOG_FILE_NAME")
    private String LogFileName;
    @Column(name = "LOG_FILE_PATH")
    private String logFilePath;
    @Column(name = "MANAGEMENT_HTTP_PORT")
    private String managementHTTPPort;
    @Column(name = "MANAGEMENT_HTTPS_PORT")
    private String managementHTTPSPort;
    @Column(name = "MANAGEMENT_CLI_PORT")
    private String managementCLIPort;
    @Column(name = "MANAGEMENT_HTTPS_URL")
    private String managementHTTPSURL;
    @Column(name = "MANAGEMENT_HTTP_URL")
    private String managementHTTPURL;
    @Column(name = "MANAGEMENT_CLI_URL")
    private String managementCLIURL;
    @Column(name = "INSTANCE_HTTP_PORT")
    private String instanceHTTPPort;
    @Column(name = "INSTANCE_HTTPS_PORT")
    private String instanceHTTPSPort;
    @Column(name = "INSTANCE_HTTP_URL")
    private String instanceHTTPURL;
    @Column(name = "INSTANCE_HTTPS_URL")
    private String instanceHTTPSURL;
    @Column(name="HOST_ID")
    private String hOSTId;
    @Column(name="APPLICATION_ID")
    private String applicationId;
    @Column(name="ENVIRONMENT_ID")
    private String environmentId;
    @Column(name="INSTALLATION_MODE_ID")
    private String installationMode;
    @Column(name="SERVER_TYPE_ID")
    private String serverType;
    @Column(name="TECHNOLOGY_ID")
    private String technology;
    @Column(name="ARTIFACTSOURCEPATH")
    private String artifactSourcePath;
    @Column(name="CLI_PATH")
    private String cliPath;
}
