package by.zbokostya.service;

import by.zbokostya.domain.Tables;
import by.zbokostya.entity.Authority;
import by.zbokostya.entity.LoginVM;
import by.zbokostya.entity.User;
import by.zbokostya.entity.api.UserApi;
import by.zbokostya.security.SecurityUtils;
import org.jooq.DSLContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final DSLContext dsl;

    private final PasswordEncoder passwordEncoder;

    public UserService(DSLContext dsl, PasswordEncoder passwordEncoder) {
        this.dsl = dsl;
        this.passwordEncoder = passwordEncoder;
    }



    public UUID createUser(LoginVM loginVM) {
        UUID uuid = UUID.randomUUID();
        dsl.insertInto(Tables.USER)
                .values(uuid,
                        loginVM.getLogin(),
                        passwordEncoder.encode(loginVM.getPassword()),
                        "").execute();
        dsl.insertInto(Tables.USER_ROLE)
                .values(uuid, "ROLE_USER")
                .execute();
        return uuid;
    }


    public User getUserWithAuthorities() {
        return findUserByUsername(SecurityUtils.getCurrentUserLogin());
    }

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

//    public String generateApiUser() {
//        User user = getUserWithAuthorities();
//        UserApi newApi = new UserApi();
//        newApi.setUuid(UUID.randomUUID());
//        apiRepository.save(newApi);
//
//        user.addApi(newApi);
//        userRepository.save(user);
//        return newApi.getUuid().toString().replace("-", "");
//    }
//
//    public List<String> getUserApiList(String login) {
//
//    }


}
