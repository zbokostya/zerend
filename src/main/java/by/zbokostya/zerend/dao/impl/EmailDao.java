package by.zbokostya.zerend.dao.impl;

import by.zbokostya.zerend.entity.VerificationToken;
import org.jooq.DSLContext;
import static by.zbokostya.generated.jooq.Tables.VERIFICATION;

public class EmailDao {
    private final DSLContext dslContext;

    public EmailDao(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    public void saveVerification(VerificationToken token) {
        dslContext.insertInto(VERIFICATION,
                VERIFICATION.ID,
                VERIFICATION.USER,
                VERIFICATION.TOKEN,
                VERIFICATION.EXPIRE_TIME)
                .values(token.getId(), token.getUserId(), token.getToken(), token.calcExpireTime())
        .execute();
    }
}
