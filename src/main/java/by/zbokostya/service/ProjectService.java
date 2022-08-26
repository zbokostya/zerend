package by.zbokostya.service;

import by.zbokostya.entity.Project;
import by.zbokostya.security.SecurityUtils;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static by.zbokostya.domain.Tables.*;


@Service
public class ProjectService {

    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private final DSLContext dsl;

    public ProjectService(DSLContext dsl) {
        this.dsl = dsl;
    }

    public void updateProject(Project project) {
        if(project.getName() != null) {
           // dsl.update
        }
    }

    public void createProject(Project project) {
        dsl.insertInto(PROJECT)
                .values(project.getId(),
                        project.getName(),
                        project.getUrl(),
                        project.getApikey(),
                        project.getOwnerId())
                .execute();
    }


    public List<Project> getAllProjects(UUID userId) {

        List<Project> allUserProjects =
                dsl
                        .selectFrom(PROJECT)
                        .where(PROJECT.OWNER.eq(userId))
                        .fetch()
                        .into(Project.class);


//        List<Project> allUserProjects =
//                dsl.selectFrom(PROJECT)
//                        .where(PROJECT.ID.in(
//                                dsl
//                                        .select(USER_PROJECT.PROJECT_ID)
//                                        .from(USER_PROJECT)
//                                        .where(USER_PROJECT.USER_ID.eq(userId))
//                                        .fetch()
//                                        .into(UUID.class)
//                        ))
//                        .fetch()
//                        .into(Project.class);
        return allUserProjects;

    }

    public Project getProjectById(UUID userId, UUID projectId) {

        return dsl.selectFrom(PROJECT)
                .where(PROJECT.ID.eq(projectId)
                        .and(PROJECT.OWNER.eq(userId)))
                .fetchOne()
                .into(Project.class);


//        return dsl.selectFrom(PROJECT)
//                .where(PROJECT.ID
//                        .eq(projectId)
//                        .and(PROJECT.ID.in(
//                                dsl
//                                        .select(USER_PROJECT.PROJECT_ID)
//                                        .from(USER_PROJECT)
//                                        .where(USER_PROJECT.USER_ID.eq(userId))
//                                        .fetch()
//                                        .into(UUID.class))))
//
//                .fetchOne()
//                .into(Project.class);

    }

    public boolean existsProjectById(UUID userId, UUID projectId) {

        return dsl.fetchExists(
                dsl.selectFrom(PROJECT)
                        .where(PROJECT.ID.eq(projectId)
                                .and(PROJECT.OWNER.eq(userId))));


//        return dsl.selectFrom(PROJECT)
//                .where(PROJECT.ID
//                        .eq(projectId)
//                        .and(PROJECT.ID.in(
//                                dsl
//                                        .select(USER_PROJECT.PROJECT_ID)
//                                        .from(USER_PROJECT)
//                                        .where(USER_PROJECT.USER_ID.eq(userId))
//                                        .fetch()
//                                        .into(UUID.class))))
//
//                .fetchOne()
//                .into(Project.class);

    }


    public UUID getCurrentUserUUID() {
        String login = SecurityUtils.getCurrentUserLogin();
        return dsl.selectFrom(USER)
                .where(USER.LOGIN.eq(login))
                .fetchOne().getId();
    }

}
