package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.IAbilityDao;
import by.zbokostya.zerend.dao.IApikeyDao;
import by.zbokostya.zerend.dao.impl.AbilityDao;
import by.zbokostya.zerend.dao.impl.ApikeyDao;
import by.zbokostya.zerend.entity.Ability;
import by.zbokostya.zerend.entity.Apikey;
import by.zbokostya.zerend.service.IApikeyAbilityService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApikeyAbilityService implements IApikeyAbilityService {

    private final IApikeyDao apikeyDao;
    private final IAbilityDao abilityDao;

    public ApikeyAbilityService(AbilityDao abilityDao, ApikeyDao apikeyDao) {
        this.abilityDao = abilityDao;
        this.apikeyDao = apikeyDao;
    }

    @Override
    public Ability getById(UUID apikeyId, UUID projectId, UUID abilityId) {
        Apikey apikey = apikeyDao.find(apikeyId);
        if (!apikey.getProject().equals(projectId))
            throw new RuntimeException("ApikeyAbilityService");
        Ability ability = abilityDao.get(abilityId);
        if (!ability.getProject().equals(projectId))
            throw new RuntimeException("ApikeyAbilityService");
        return ability;
    }

    @Override
    public Ability insert(UUID apikeyId, UUID projectId, Ability ability) {
        Apikey apikey = apikeyDao.find(apikeyId);
        if (!apikey.getProject().equals(projectId))
            throw new RuntimeException("ApikeyAbilityService");
        return abilityDao.insert(ability);
    }
}
