package com.blacky.scale.model;

import java.io.Serializable;

public class Project implements Serializable {
    private Long id;
    private Long version;
    private String data;

    public Project() {}

    public Project(Long id, Long version, String data) {
        this.id = id;
        this.version = version;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getData() {
        return data;
    }
}
