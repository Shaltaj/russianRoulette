package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class GameDAOImpl implements GameDAO {
    private EntityManager em;

    public GameDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Game HostGame(Player player) {
        if (CurrentGame(player) != null) {
            throw new IllegalArgumentException("Current game already exist");
        }
        Game game = new Game(player);
        UpdateGame(game);
        return game;
    }

    @Override
    public void JoinGame(Game game, Player player) {
        if (CurrentGame(player) != null) {
            throw new IllegalArgumentException("Current game already exist");
        }
        game.setPlayer2(player);
        UpdateGame(game);

    }

    @Override
    public Game CurrentGame(Player player) {
        try {
            return em.createQuery("SELECT g FROM GAMES g WHERE (g.player1 = :player or g.player2 = :player) and g.gameClosed = false", Game.class)
                    .setParameter("player", player)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Game> FindGames(int count) {
        return em.createQuery("SELECT g FROM GAMES g WHERE g.gameClosed = false", Game.class)
                .setFirstResult(0)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public void UpdateGame(Game game) {
        em.getTransaction().begin();
        try {
            em.persist(game);
            em.getTransaction().commit();

        } catch (PersistenceException e){
            em.getTransaction().rollback();
            throw e;

        }
    }


}
