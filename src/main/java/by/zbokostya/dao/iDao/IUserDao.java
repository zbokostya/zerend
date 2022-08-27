package by.zbokostya.dao.iDao;

import by.zbokostya.entity.input.LoginInput;
import by.zbokostya.entity.User;

import java.util.UUID;

public interface IUserDao {

    UUID createUser(LoginInput loginInput);

    User findUserByUsername(String login);
}
