package by.zbokostya.dao.impl;

import by.zbokostya.dao.IProjectDao;
import by.zbokostya.entity.Project;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static by.zbokostya.domain.Tables.PROJECT;

@Repository
public class ProjectDao extends JOOQGenericDao<Project, UUID> implements IProjectDao {

    public ProjectDao(DSLContext dslContext) {
        super(Project.class, PROJECT, dslContext);
    }


    @Override
    public List<Project> getByOwner(UUID userId) {
        return fetch(PROJECT.OWNER.eq(userId));
    }

//    @Override
//    public Project getByIdAndOwner(UUID projectId, UUID userId) {
//        return fetchOne(PROJECT.ID.eq(projectId)
//                .and(PROJECT.OWNER.eq(userId))).orElse(null);
//    }

    @Override
    public boolean existsByIdAndOwner(UUID projectId, UUID userId) {
        return getDSLContext().fetchExists(
                getDSLContext().selectFrom(PROJECT)
                        .where(PROJECT.ID.eq(projectId)
                                .and(PROJECT.OWNER.eq(userId))));
    }

    @Override
    public Project insert(Project project) {
        return null;
    }

    @Override
    public int deleteById(UUID uuid) {
        return 0;
    }

}
