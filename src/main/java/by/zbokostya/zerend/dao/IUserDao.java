package by.zbokostya.zerend.dao;

import by.zbokostya.zerend.entity.input.LoginInput;
import by.zbokostya.zerend.entity.User;

import java.util.UUID;

public interface IUserDao extends IDao<User, UUID> {

    UUID createUser(LoginInput loginInput);

    User findUserByUsername(String login);
}
