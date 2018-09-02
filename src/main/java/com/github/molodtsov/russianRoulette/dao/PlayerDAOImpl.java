package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {
    private EntityManager em;

    public PlayerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Player SignInPlayer(String login, String password) {
        return em.createQuery("SELECT p FROM Player p WHERE p.login = :login and p.password = :password", Player.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }

    @Override
    public Player RegisterPlayer(String name, String login, String password) {
        return null;
    }

    @Override
    public List<Player> TopPlayers(int count) {
        return null;
    }

    @Override
    public void Add(Player player) {
        em.getTransaction().begin();
        try {
            em.persist(player);
            em.getTransaction().commit();

        } catch (PersistenceException e){
            em.getTransaction().rollback();
            throw e;

        }

    }
}
