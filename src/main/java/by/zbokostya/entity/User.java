package by.zbokostya.entity;

import by.zbokostya.entity.api.UserApi;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<UserApi> getApis() {
        return apis;
    }

    public void setApis(Set<UserApi> apis) {
        this.apis = apis;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }
}
