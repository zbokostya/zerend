package by.zbokostya.service;

import by.zbokostya.dao.ProjectDao;
import by.zbokostya.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    private final ProjectDao projectDao;

    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public void updateProject(Project project) {
        if (project.getName() != null) {
            // dsl.update
        }
    }

    public void createProject(Project project) {
        projectDao.createProject(project);
    }


    public List<Project> getAllProjects(UUID userId) {
        return projectDao.getAllProjects(userId);
    }

    public Project getProjectById(UUID userId, UUID projectId) {
        return projectDao.getProjectById(userId, projectId);
    }

    public boolean existsProjectById(UUID userId, UUID projectId) {
        return projectDao.existsProjectById(userId, projectId);
    }

}
