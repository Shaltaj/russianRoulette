package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Player;

import java.util.List;

public interface PlayerDAO {
    Player signInPlayer(String login, String password);
    Player registerPlayer(String name, String login, String password);
    List<Player> topPlayers(int count);
    void updatePlayer(Player player);
}
