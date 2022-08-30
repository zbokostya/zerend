package by.zbokostya.dao.impl;

import by.zbokostya.dao.IAbilityDao;
import by.zbokostya.entity.Ability;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static by.zbokostya.domain.tables.Ability.ABILITY;

@Repository
public class AbilityDao extends JOOQGenericDao<Ability, UUID> implements IAbilityDao {

    public AbilityDao(DSLContext dslContext) {
        super(Ability.class, ABILITY, dslContext);
    }

}
