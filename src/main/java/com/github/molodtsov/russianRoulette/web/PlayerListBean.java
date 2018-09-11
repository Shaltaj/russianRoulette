package com.github.molodtsov.russianRoulette.web;

import com.github.molodtsov.russianRoulette.model.Player;

import java.util.List;

public class PlayerListBean {
    private String userAgent;
    private List<Player> playerList;

    public PlayerListBean(String userAgent, List<Player> playerList) {
        this.userAgent = userAgent;
        this.playerList = playerList;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
