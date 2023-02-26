package by.zbokostya.zerend.service.impl;

import by.zbokostya.zerend.dao.impl.UserDao;
import by.zbokostya.zerend.entity.User;
import by.zbokostya.zerend.entity.input.LoginInput;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UUID createUser(LoginInput loginInput) {
        userDao.
        return userDao.createUser(loginInput);
    }

    public User findUserByUsername(String login) {
        return userDao.findUserByUsername(login);
    }

    public User get(UUID uuid) {
        return userDao.get(uuid);
    }


}
