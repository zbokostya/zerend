package by.zbokostya.dao.impl;

import by.zbokostya.dao.IUserDao;
import by.zbokostya.domain.Tables;
import by.zbokostya.entity.Authority;
import by.zbokostya.entity.User;
import by.zbokostya.entity.input.LoginInput;
import org.jooq.DSLContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserDao implements IUserDao {

    private final DSLContext dsl;
    private final PasswordEncoder passwordEncoder;

    public UserDao(DSLContext dsl, PasswordEncoder passwordEncoder) {
        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UUID createUser(LoginInput loginInput) {
        UUID uuid = UUID.randomUUID();
        dsl.insertInto(Tables.USER)
                .values(uuid,
                        loginInput.getLogin(),
                        passwordEncoder.encode(loginInput.getPassword()),
                        "").execute();
        dsl.insertInto(Tables.USER_ROLE)
                .values(uuid, "ROLE_USER")
                .execute();
        return uuid;
    }

    @Override
    public User findUserByUsername(String login) {
        return dsl
                .fetchOne(Tables.USER, Tables.USER.LOGIN.eq(login))
                .map(
                        r -> {
                            User user = r.into(User.class);
                            user.setAuthorities(
                                    dsl.select(Tables.USER_ROLE.USER_ROLE_)
                                            .from(Tables.USER_ROLE)
                                            .where(Tables.USER_ROLE.USER_ID.eq(user.getId()))
                                            .fetchInto(Authority.class)
                            );
                            return user;
                        }
                );
    }
}
