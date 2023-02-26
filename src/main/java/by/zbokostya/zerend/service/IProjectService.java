package by.zbokostya.zerend.service;

import by.zbokostya.zerend.entity.Project;

import java.util.List;
import java.util.UUID;

public interface IProjectService {

    boolean existsProjectById(UUID userId, UUID projectId);

    Project getProjectById(UUID userId, UUID projectId);

    List<Project> getAllProjects(UUID userId);

    void createProject(Project project);

    void updateProject(Project project);
}
