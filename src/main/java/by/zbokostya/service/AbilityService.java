package by.zbokostya.service;

import by.zbokostya.dao.IAbilityDao;
import by.zbokostya.dao.IProjectDao;
import by.zbokostya.dao.IUserDao;
import by.zbokostya.entity.Project;
import by.zbokostya.entity.Ability;
import by.zbokostya.service.error.ForbiddenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AbilityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IAbilityDao abilityDao;
    private final IUserDao userDao;
    private final IProjectDao projectDao;

    public AbilityService(IAbilityDao abilityDao, IUserDao userDao, IProjectDao projectDao) {
        this.abilityDao = abilityDao;
        this.userDao = userDao;
        this.projectDao = projectDao;
    }


    public Ability getAbilityById(UUID userId, UUID projectId, UUID abilityId) {
        Ability ability = abilityDao.get(abilityId);
        if (ability == null || !ability.getProject().equals(projectId))
            throw new ForbiddenException("abc");
        Project project = projectDao.get(projectId);
        if (project == null || !project.getOwner().equals(userId))
            throw new ForbiddenException("abc");
        return ability;
    }

    public Ability createAbility(Ability ability) {
        return abilityDao.insert(ability);
    }
}
