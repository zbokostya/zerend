package by.zbokostya.entity;

import by.zbokostya.entity.api.UserApi;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//@Entity
//@Table(name = "\"user\"")
@Data
public class User {


    private UUID id;
    private String login;
    private String password;
    private String email;

    private Set<UserApi> apis = new HashSet<>();

    private List<Authority> authorities;

    public void addApi(UserApi api) {
        this.apis.add(api);
    }
}
