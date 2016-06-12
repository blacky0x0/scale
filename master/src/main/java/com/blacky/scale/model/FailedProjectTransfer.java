package com.blacky.scale.model;

import java.io.Serializable;

/**
 * DTO
 */
public class FailedProjectTransfer implements Serializable {
    private Integer slavePort;
    private Integer responseStatus;
    private Long projectId;
    private Long version;
    private String data;

    public FailedProjectTransfer() {}

    public FailedProjectTransfer(Integer slavePort, Integer responseStatus, Project project) {
        this.slavePort = slavePort;
        this.responseStatus = responseStatus;
        this.projectId = project.getId();
        this.version = project.getVersion();
        this.data = project.getData();
    }

    public Integer getSlavePort() {
        return slavePort;
    }

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getVersion() {
        return version;
    }

    public String getData() {
        return data;
    }
}
