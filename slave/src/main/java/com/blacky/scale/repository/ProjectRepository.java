package com.blacky.scale.repository;

import com.blacky.scale.model.Project;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.collect.Lists.newArrayList;

@Repository
public class ProjectRepository {
    private ConcurrentHashMap<Long, Project> storage = new ConcurrentHashMap<>();

    public void add(Project project) {
        storage.put(project.getId(), project);
    }

    public List<Project> getAll() {
        return newArrayList(storage.values());
    }
}
