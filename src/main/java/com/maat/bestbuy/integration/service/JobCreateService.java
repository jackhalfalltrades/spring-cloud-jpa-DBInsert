package com.maat.bestbuy.integration.service;

import com.maat.bestbuy.integration.model.Payload;
import com.maat.bestbuy.integration.model.Response;

public interface JobCreateService {
    //String createJob(Payload payload);
    Response jobCreate(Payload payload);
}
