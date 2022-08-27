package by.zbokostya.dao;

import by.zbokostya.dao.iDao.IProjectDao;
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
    public void createProject(Project project) {
        insert(project);
    }

    @Override
    public List<Project> getAllProjects(UUID userId) {
        return fetch(PROJECT.OWNER.eq(userId));
    }

    @Override
    public Project getProjectById(UUID userId, UUID projectId) {
        return fetchOne(PROJECT.OWNER.eq(userId)).get();
//        return dsl.selectFrom(PROJECT)
//                .where(PROJECT.ID.eq(projectId)
//                        .and(PROJECT.OWNER.eq(userId)))
//                .fetchOne()
//                .into(Project.class);
    }

    @Override
    public boolean existsProjectById(UUID userId, UUID projectId) {

        return getDSLContext().fetchExists(
                getDSLContext().selectFrom(PROJECT)
                        .where(PROJECT.ID.eq(projectId)
                                .and(PROJECT.OWNER.eq(userId))));

//        return dsl.fetchExists(
//                dsl.selectFrom(PROJECT)
//                        .where(PROJECT.ID.eq(projectId)
//                                .and(PROJECT.OWNER.eq(userId))));
    }
}
