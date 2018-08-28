package com.github.molodtsov.russianRoulette.model;

public class Player {
    private String name;
    private String login;
    private String password;
    private int money;
    private int win;
    private int lose;



    public Player(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.money = 10;
        this.win = 0;
        this.lose = 0;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public void addWin() {
        this.win ++;
    }

    public int getLose() {
        return lose;
    }

    public void addLose() {
        this.lose ++;
    }
}
