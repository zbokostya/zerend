package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.IApikeyDao;
import by.zbokostya.zerend.dao.IProjectDao;
import by.zbokostya.zerend.entity.Apikey;
import by.zbokostya.zerend.entity.Project;
import by.zbokostya.zerend.service.IApikeyProjectService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApikeyProjectService implements IApikeyProjectService {
    private final IApikeyDao apikeyDao;
    private final IProjectDao projectDao;

    public ApikeyProjectService(IApikeyDao apikeyDao, IProjectDao projectDao) {
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
