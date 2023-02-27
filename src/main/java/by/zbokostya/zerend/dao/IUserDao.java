package by.zbokostya.zerend.dao;

import by.zbokostya.zerend.entity.input.LoginInput;
import by.zbokostya.zerend.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserDao extends IDao<User, UUID> {

    UUID createUser(User user);

    User findUserByUsername(String login);

    Optional<User> findUserByEmail(String email);
    boolean checkExistsUserByEmail(String email);
}
