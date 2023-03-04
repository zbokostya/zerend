package by.zbokostya.zerend.dao.impl;

import by.zbokostya.zerend.entity.VerificationToken;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static by.zbokostya.generated.jooq.Tables.VERIFICATION;

@Repository
public class EmailDao extends JOOQGenericDao<VerificationToken, UUID> {

    public EmailDao(DSLContext dslContext) {
        super(VerificationToken.class, VERIFICATION, dslContext);
    }

    public void saveVerification(VerificationToken token) {
        super.insert(token);
    }

    public Optional<VerificationToken> getTokenByToken(String token) {
        return super.fetchOne(VERIFICATION.TOKEN.eq(token)
                .and(VERIFICATION.EXPIRE_TIME.greaterOrEqual(Instant.now())));
    }

}
