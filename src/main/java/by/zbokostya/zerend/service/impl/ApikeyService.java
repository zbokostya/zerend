package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.impl.ApikeyDao;
import by.zbokostya.zerend.entity.Apikey;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static by.zbokostya.zerend.entity.EntityUtils.generate;

@Service
public class ApikeyService {

    private final ApikeyDao apikeyDao;

    public ApikeyService(ApikeyDao apikeyDao) {
        this.apikeyDao = apikeyDao;
    }

    public Apikey findByUsername(String login) {
        return apikeyDao.findByUsername(login);
    }

    public Apikey getApiKey(UUID projectId) {
        if(!apikeyDao.checkExists(projectId)) {
            Apikey apikey = new Apikey();
            apikey.setId(UUID.randomUUID());
            apikey.setApikey(generate(128));
            apikey.setProject(projectId);
            apikey.setRole("ROLE_VIEWER");
            apikeyDao.insert(apikey);
        }
        return apikeyDao.findByProjectId(projectId);
    }

}
