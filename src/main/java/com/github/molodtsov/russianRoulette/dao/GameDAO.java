package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;

import java.util.List;

public interface GameDAO {
    Game hostGame(Player player);
    void joinGame(Game game, Player player);
    Game currentGame(Player player);
    List<Game> findGames(int count);
    void updateGame(Game game);
}
