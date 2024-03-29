package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.IProjectDao;
import by.zbokostya.zerend.dao.impl.ProjectDao;
import by.zbokostya.zerend.entity.Project;
import by.zbokostya.zerend.service.IProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService implements IProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    private final IProjectDao projectDao;

    public ProjectService(ProjectDao projectDao) {
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
        return projectDao.get(projectId);
    }


    public boolean existsProjectById(UUID userId, UUID projectId) {
        return projectDao.existsByIdAndOwner(projectId, userId);
    }

}
