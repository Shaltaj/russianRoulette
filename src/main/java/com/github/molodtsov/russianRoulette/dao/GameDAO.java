package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;

import java.util.List;

public interface GameDAO {
    Game HostGame(Player player);
    Game JoinGame(Player player);
    Game CurrentGame(Player player);
    List<Game> FindGames();
    void Add(Game game);
}
