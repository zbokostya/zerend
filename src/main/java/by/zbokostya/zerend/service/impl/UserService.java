package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.impl.UserDao;
import by.zbokostya.zerend.entity.Authority;
import by.zbokostya.zerend.entity.User;
import by.zbokostya.zerend.entity.input.LoginInput;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static by.zbokostya.generated.jooq.Tables.USER_ROLE;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;


    public UserService(UserDao userDao, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public UUID createUser(LoginInput loginInput) {
        if(userDao.checkExistsUserByEmail(loginInput.getEmail())) {
            User user = userDao.findUserByEmail(loginInput.getEmail()).get();
            boolean isEnabled = user.isEnabled();
            if(!isEnabled) {
                //TODO
                String token = UUID.randomUUID().toString();
                saveConfirmationToken(appUserPrevious, token);

            } else {
                //todo
                throw new RuntimeException();
            }

        }
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setLogin(loginInput.getLogin());
        user.setEmail(loginInput.getEmail());
        user.setAuthorities(List.of(new Authority("ROLE_USER")));
        user.setPassword(passwordEncoder.encode(loginInput.getPassword()));
        return userDao.createUser(user);
    }

    public User findUserByUsername(String login) {
        return userDao.findUserByUsername(login);
    }

    public User get(UUID uuid) {
        return userDao.get(uuid);
    }


}
