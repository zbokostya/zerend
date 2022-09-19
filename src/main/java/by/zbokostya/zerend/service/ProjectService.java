package by.zbokostya.zerend.service;

import by.zbokostya.zerend.dao.IApikeyDao;
import by.zbokostya.zerend.dao.IProjectDao;
import by.zbokostya.zerend.entity.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);

    private final IProjectDao projectDao;
    private final IApikeyDao apikeyDao;

    public ProjectService(IProjectDao projectDao, IApikeyDao apikeyDao) {
        this.projectDao = projectDao;
        this.apikeyDao = apikeyDao;
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
        return project;
    }


    public boolean existsProjectById(UUID userId, UUID projectId) {
        return projectDao.existsByIdAndOwner(projectId, userId);
    }

}
