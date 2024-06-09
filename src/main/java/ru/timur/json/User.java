package ru.timur.json;

import java.util.List;

public class User {
    String login;
    String email;
    List<String> roles;

    public User(String login, String email, List<String> roles) {
        this.login = login;
        this.email = email;
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
