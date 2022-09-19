package by.zbokostya.zerend.service;

import by.zbokostya.zerend.dao.impl.ApikeyDao;
import by.zbokostya.zerend.entity.Apikey;
import org.springframework.stereotype.Service;

@Service
public class ApikeyService {

    private final ApikeyDao apikeyDao;

    public ApikeyService(ApikeyDao apikeyDao) {
        this.apikeyDao = apikeyDao;
    }

    public Apikey findByUsername(String login) {
        return apikeyDao.findByUsername(login);
    }
}
