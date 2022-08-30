package by.zbokostya.service;

import by.zbokostya.dao.impl.UserDao;
import by.zbokostya.entity.User;
import by.zbokostya.entity.input.LoginInput;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UUID createUser(LoginInput loginInput) {
        return userDao.createUser(loginInput);
    }

    public User findUserByUsername(String login) {
        return userDao.findUserByUsername(login);
    }


}
