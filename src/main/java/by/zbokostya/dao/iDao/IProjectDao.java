package by.zbokostya.dao.iDao;

import by.zbokostya.entity.Project;

import java.util.List;
import java.util.UUID;

public interface IProjectDao {
    void createProject(Project project);
    List<Project>  getAllProjects(UUID userId);
    Project getProjectById(UUID userId, UUID projectId);
    boolean existsProjectById(UUID userId, UUID projectId);
}
