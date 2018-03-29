package com.maat.bestbuy.integration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Payload implements Serializable
{
    private final static long serialVersionUID = 8125502179272231981L;

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("requestTypeOptionsModel")
    private List<String> requestTypeOptionsModel = new ArrayList<String>();
    @JsonProperty("ticketNumber")
    private String ticketNumber;
    @JsonProperty("cerNo")
    private String cerNo;
    @JsonProperty("spid")
    private String spid;
    @JsonProperty("impactedCi")
    private String impactedCi;
    @JsonProperty("applicationOptionsModel")
    private List<String> applicationOptionsModel = new ArrayList<String>();
    @JsonProperty("environmentOptionsModel")
    private List<String> environmentOptionsModel = new ArrayList<String>();
    @JsonProperty("typeOptionsModel")
    private List<String> typeOptionsModel = new ArrayList<String>();
    @JsonProperty("domainOptionsModel")
    private List<String> domainOptionsModel = new ArrayList<String>();
    @JsonProperty("clusterOptionsModel")
    private List<String> clusterOptionsModel = new ArrayList<String>();
    @JsonProperty("vmOptionsModel")
    private List<String> vmOptionsModel = new ArrayList<String>();
    @JsonProperty("jvmOptionsModel")
    private List<String> mappingId = new ArrayList<String>();
    @JsonProperty("taskOptionsModel")
    private List<String> taskOptionsModel = new ArrayList<String>();
    @JsonProperty("artifactOptionsModel")
    private List<String> artifactOptionsModel = new ArrayList<String>();
    @JsonProperty("artifactPathOptionsModel")
    private List<String> artifactPathOptionsModel = new ArrayList<String>();
    @JsonProperty("isScheduled")
    private String isScheduled;
}
