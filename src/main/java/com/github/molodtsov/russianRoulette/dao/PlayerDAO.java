package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Player;

import java.util.List;

public interface PlayerDAO {
    Player SignInPlayer(String login, String password);
    Player RegisterPlayer(String name, String login, String password);
    List<Player> TopPlayers(int count);
    void UpdatePlayer(Player player);
}
