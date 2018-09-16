package com.github.molodtsov.russianRoulette.web;

import javax.validation.constraints.Size;

public class RegisterPlayerFormBean {
    @Size(min = 5, max = 50)
    private String name = "";
    @Size(min = 5, max = 20)
    private String login = "";
    @Size(min = 5, max = 10)
    private String password = "";

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
