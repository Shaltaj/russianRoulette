package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class GameDAOImpl extends UpdateObject<Game> implements GameDAO {

    @Autowired
    public GameDAOImpl(EntityManager em) {
        super(em);
    }


    @Override
    public Game hostGame(Player player) {
        if (currentGame(player) != null) {
            throw new IllegalArgumentException("Current game already exist");
        }
        Game game = new Game(player);
        updateGame(game);
        return game;
    }

    @Override
    public void joinGame(Game game, Player player) {
        if (currentGame(player) != null) {
            throw new IllegalArgumentException("Current game already exist");
        }
        game.setPlayer2(player);
        updateGame(game);

    }

    @Override
    public Game currentGame(Player player) {
        try {
            return em.createQuery("SELECT g FROM GAMES g WHERE (g.player1 = :player or g.player2 = :player) and g.gameClosed = false", Game.class)
                    .setParameter("player", player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Game> findGames(int count) {
        return em.createQuery("SELECT g FROM GAMES g WHERE g.gameClosed = false", Game.class)
                .setFirstResult(0)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public void updateGame(Game game) {
        update(game);
    }


}
