package by.zbokostya.service;

import by.zbokostya.dao.IProjectDao;
import by.zbokostya.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    private final IProjectDao projectDao;

    public ProjectService(IProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public void updateProject(Project project) {
        if (project.getName() != null) {
            // dsl.update
        }
    }

    public void createProject(Project project) {
        projectDao.insert(project);
    }


    public List<Project> getAllProjects(UUID userId) {
        return projectDao.getByOwner(userId);
    }

    public Project getProjectById(UUID userId, UUID projectId) {
        Project project = projectDao.get(projectId);
        if(!project.getOwner().equals(userId)) return null;
        return project;
    }

    public boolean existsProjectById(UUID userId, UUID projectId) {
        return projectDao.existsByIdAndOwner(projectId, userId);
    }

}
