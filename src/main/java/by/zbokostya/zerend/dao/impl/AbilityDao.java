package by.zbokostya.zerend.dao.impl;

import by.zbokostya.zerend.dao.IAbilityDao;
import by.zbokostya.zerend.entity.Ability;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static by.zbokostya.generated.jooq.tables.Ability.ABILITY;

@Repository
public class AbilityDao extends JOOQGenericDao<Ability, UUID> implements IAbilityDao {

    public AbilityDao(DSLContext dslContext) {
        super(Ability.class, ABILITY, dslContext);
    }

}
