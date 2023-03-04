package by.zbokostya.zerend.dao.impl;

import by.zbokostya.generated.jooq.Tables;
import by.zbokostya.zerend.dao.IUserDao;
import by.zbokostya.zerend.dao.error.NoSuchUserException;
import by.zbokostya.zerend.entity.Authority;
import by.zbokostya.zerend.entity.User;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static by.zbokostya.generated.jooq.Tables.USER;
import static by.zbokostya.generated.jooq.Tables.USER_ROLE;

@Component
public class UserDao extends JOOQGenericDao<User, UUID> implements IUserDao {


    public UserDao(DSLContext dslContext) {
        super(User.class, USER, dslContext);
    }

    @Override
    @Transactional
    public UUID createUser(User user) {
        insert(user);
        getDSLContext().insertInto(USER_ROLE)
                .values(user.getId(), "ROLE_USER")
                .execute();
        return user.getId();
    }

    @Override
    public User findUserByUsername(String login) {
        return getDSLContext()
                .fetchOptional(USER, USER.LOGIN.eq(login))
                .orElseThrow(() -> new NoSuchUserException("No such user with this login"))
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
    public Optional<User> findUserByEmail(String email) {
        return super
                .fetchOne(USER.EMAIL.eq(email));
    }

    @Override
    public boolean checkExistsUserByEmail(String email) {
        return findUserByEmail(email).isPresent();
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

    public void setEnabled(UUID id) {
        getDSLContext().update(USER)
                .set(USER.ENABLED, true)
                .where(USER.ID.eq(id))
                .execute();
    }

    @Override
    public int deleteById(UUID uuid) {
        return 0;
    }
}
