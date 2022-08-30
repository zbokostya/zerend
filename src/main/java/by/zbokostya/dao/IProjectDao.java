package by.zbokostya.dao;

import by.zbokostya.entity.Project;

import java.util.List;
import java.util.UUID;

public interface IProjectDao extends IDao<Project, UUID> {
    List<Project> getByOwner(UUID userId);
    boolean existsByIdAndOwner(UUID projectId, UUID userId);
}
