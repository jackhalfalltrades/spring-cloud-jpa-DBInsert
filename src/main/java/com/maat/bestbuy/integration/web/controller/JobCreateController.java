package com.maat.bestbuy.integration.web.controller;

import com.maat.bestbuy.integration.model.Payload;
import com.maat.bestbuy.integration.model.Response;
import com.maat.bestbuy.integration.service.JobCreateServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JobCreateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCreateController.class);

    private JobCreateServiceImpl jobCreateService;

    @Autowired
    public JobCreateController (JobCreateServiceImpl jobCreateService) {
        this.jobCreateService = jobCreateService;
    }

    @PostMapping(value = "/jobCreate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody @CrossOrigin
    Response jobCreate(@RequestBody @Valid Payload payload) {

        LOGGER.debug("JobCreate (): -> Payload: {}", payload);
        LOGGER.info("JobCreate (): -> Intiated!!!");
        Response response = jobCreateService.jobCreate(payload);
        LOGGER.info("JobCreate(): -> Completed Succfully with Job ID : {}", response.getJobID());
        return response;
    }

}
