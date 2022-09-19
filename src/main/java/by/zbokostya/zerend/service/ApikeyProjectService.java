package by.zbokostya.zerend.service;

import by.zbokostya.zerend.dao.impl.ApikeyDao;
import by.zbokostya.zerend.dao.impl.ProjectDao;
import by.zbokostya.zerend.entity.Apikey;
import by.zbokostya.zerend.entity.Project;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApikeyProjectService {
    private final ApikeyDao apikeyDao;
    private final ProjectDao projectDao;

    public ApikeyProjectService(ApikeyDao apikeyDao, ProjectDao projectDao) {
        this.apikeyDao = apikeyDao;
        this.projectDao = projectDao;
    }


    public Apikey findByUsername(String login) {
        return apikeyDao.findByUsername(login);
    }

    public Project findByApikey(UUID apikeyId) {
        Apikey apikey = apikeyDao.find(apikeyId);
        return projectDao.get(apikey.getProject());
    }



}
