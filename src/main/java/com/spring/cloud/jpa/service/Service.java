package com.spring.cloud.jpa.service;

import com.spring.cloud.jpa.model.Payload;
import com.spring.cloud.jpa.model.Response;

public interface Service {
    //String createJob(Payload payload);
    Response jobCreate(Payload payload);
}
