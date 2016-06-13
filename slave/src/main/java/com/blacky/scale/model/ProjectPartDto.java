package com.blacky.scale.model;

import java.io.Serializable;

public class ProjectPartDto implements Serializable {
    private Long version;
    private String data;

    public ProjectPartDto() {}

    public ProjectPartDto(Long version, String data) {
        this.version = version;
        this.data = data;
    }

    public Long getVersion() {
        return version;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        return "ProjectDto{" +
                "version=" + version +
                ", data='" + data + '\'' +
                '}';
    }
}
