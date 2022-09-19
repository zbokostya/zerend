package by.zbokostya.zerend.dao.impl;

import by.zbokostya.generated.jooq.Tables;
import by.zbokostya.zerend.dao.IUserDao;
import by.zbokostya.zerend.entity.Authority;
import by.zbokostya.zerend.entity.User;
import by.zbokostya.zerend.entity.input.LoginInput;
import org.jooq.DSLContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static by.zbokostya.generated.jooq.Tables.USER;
import static by.zbokostya.generated.jooq.Tables.USER_ROLE;

@Component
public class UserDao extends JOOQGenericDao<User, UUID> implements IUserDao {

    private final PasswordEncoder passwordEncoder;

    public UserDao(DSLContext dslContext, PasswordEncoder passwordEncoder) {
        super(User.class, USER, dslContext);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UUID createUser(LoginInput loginInput) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setLogin(loginInput.getLogin());
        //todo
        user.setEmail("");
        user.setAuthorities(List.of(new Authority("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(loginInput.getPassword()));
        insert(user);

        getDSLContext().insertInto(USER_ROLE)
                .values(user.getId(), "ROLE_USER")
                .execute();
        return user.getId();
    }

    @Override
    public User findUserByUsername(String login) {
        return getDSLContext()
                .fetchOne(USER, USER.LOGIN.eq(login))
                .map(
                        r -> {
                            User user = r.into(User.class);
                            user.setAuthorities(
                                    getDSLContext().select(Tables.USER_ROLE.USER_ROLE_)
                                            .from(Tables.USER_ROLE)
                                            .where(Tables.USER_ROLE.USER_ID.eq(user.getId()))
                                            .fetchInto(Authority.class)
                            );
                            return user;
                        }
                );
    }

    @Override
    public User insert(User entity) {
        return super.insert(entity);
    }

    @Override
    public User get(UUID uuid) {
        return super.get(uuid);
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public int deleteById(UUID uuid) {
        return 0;
    }
}
