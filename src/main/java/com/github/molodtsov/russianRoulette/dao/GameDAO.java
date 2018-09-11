package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;

import java.util.List;

public interface GameDAO {
    Game HostGame(Player player);
    void JoinGame(Game game, Player player);
    Game CurrentGame(Player player);
    List<Game> FindGames(int count);
    void UpdateGame(Game game);
}
