package com.blacky.scale.model;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(version, project.version) &&
                Objects.equals(data, project.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
