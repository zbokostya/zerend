package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.impl.EmailDao;
import by.zbokostya.zerend.dao.impl.UserDao;
import by.zbokostya.zerend.entity.Authority;
import by.zbokostya.zerend.entity.User;
import by.zbokostya.zerend.entity.VerificationToken;
import by.zbokostya.zerend.entity.input.LoginInput;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final EmailDao emailDao;


    public UserService(UserDao userDao, PasswordEncoder passwordEncoder, EmailService emailService, EmailDao emailDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.emailDao = emailDao;
    }

    public UUID createUser(LoginInput loginInput) {
        if (userDao.checkExistsUserByEmail(loginInput.getEmail())) {
            User user = userDao.findUserByEmail(loginInput.getEmail()).get();
            boolean isEnabled = user.isEnabled();
            if (!isEnabled) {
                String token = saveVerificationToken(user);
                emailService.sendConfirmationEmail(user, token);
                return user.getId();
            } else {
                //todo
                throw new RuntimeException();
            }

        } else {
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setLogin(loginInput.getLogin());
            user.setEmail(loginInput.getEmail());
            user.setAuthorities(List.of(new Authority("ROLE_USER")));
            user.setPassword(passwordEncoder.encode(loginInput.getPassword()));
            userDao.createUser(user);
            String token = saveVerificationToken(user);
            emailService.sendConfirmationEmail(user, token);

            return user.getId();
        }
    }

    public UUID verifyUser(String token) {
        Optional<VerificationToken> vt = emailDao.getTokenByToken(token);
        if(vt.isPresent()) {
            userDao.setEnabled(vt.get().getUser());
            return vt.get().getUser();
        } else {
            //todo
            throw new RuntimeException();
        }
    }

    private String saveVerificationToken(User user) {
        VerificationToken token = new VerificationToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user.getId());
        token.setId(UUID.randomUUID());
        emailDao.saveVerification(token);
        return token.getToken();
    }

    public User findUserByUsername(String login) {
        return userDao.findUserByUsername(login);
    }

    public User get(UUID uuid) {
        return userDao.get(uuid);
    }


}
