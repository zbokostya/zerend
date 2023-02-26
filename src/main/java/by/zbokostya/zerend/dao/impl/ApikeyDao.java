package by.zbokostya.zerend.dao.impl;

import by.zbokostya.zerend.dao.IApikeyDao;
import by.zbokostya.zerend.entity.Apikey;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static by.zbokostya.generated.jooq.tables.Apikey.APIKEY;

@Repository
public class ApikeyDao implements IApikeyDao {
    private final DSLContext dslContext;

    public ApikeyDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public Apikey findByUsername(String username) {
        return dslContext
                .fetchOptional(APIKEY, APIKEY.APIKEY_.eq(username))
                .orElseThrow(() -> new RuntimeException("ApikeyDao"))
                .into(Apikey.class);
    }

    @Override
    public Apikey find(UUID uuid) {
        return dslContext
                .fetchOptional(APIKEY, APIKEY.ID.eq(uuid))
                .orElseThrow(() -> new RuntimeException("ApikeyDao"))
                .into(Apikey.class);
    }

    public void insert(Apikey apikey) {
        dslContext
                .insertInto(APIKEY, APIKEY.ID, APIKEY.PROJECT, APIKEY.APIKEY_, APIKEY.ROLE)
                .values(apikey.getId(), apikey.getProject(), apikey.getApikey(), apikey.getRole())
                .execute();
    }

    public Apikey findByProjectId(UUID uuid) {
        return dslContext
                .fetchOptional(APIKEY, APIKEY.PROJECT.eq(uuid))
                .orElseThrow(() -> new RuntimeException("ApikeyDao"))
                .into(Apikey.class);
    }

    public boolean checkExists(UUID projectId) {
        return dslContext
                .fetchExists(
                        dslContext.selectFrom(APIKEY)
                                .where(APIKEY.PROJECT.eq(projectId)
                                )
                );
    }


}
