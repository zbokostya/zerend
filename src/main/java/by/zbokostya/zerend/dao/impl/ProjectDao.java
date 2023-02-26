package by.zbokostya.zerend.dao.impl;

import by.zbokostya.zerend.dao.IProjectDao;
import by.zbokostya.zerend.entity.Project;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static by.zbokostya.generated.jooq.Tables.PROJECT;

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
}
