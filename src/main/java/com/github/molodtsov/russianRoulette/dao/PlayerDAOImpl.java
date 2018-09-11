package com.github.molodtsov.russianRoulette.dao;

import com.github.molodtsov.russianRoulette.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {
    private EntityManager em;

    public PlayerDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Player SignInPlayer(String login, String password) {
        try {

            return em.createQuery("SELECT p FROM Player p WHERE p.login = :login and p.password = :password", Player.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Player RegisterPlayer(String name, String login, String password) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Name shouldn't be empty");
        }
        if (login.equals("")) {
            throw new IllegalArgumentException("Login shouldn't be empty");
        }
        if (password.equals("")) {
            throw new IllegalArgumentException("Password shouldn't be empty");
        }
        Player player = new Player(name, login, password);
        UpdatePlayer(player);

        return player;
    }

    @Override
    public List<Player> TopPlayers(int count) {
        return em.createQuery("SELECT p FROM Player p ORDER BY p.money desc", Player.class)
                .setFirstResult(0)
                .setMaxResults(count)
                .getResultList();
    }

    @Override
    public void UpdatePlayer(Player player) {
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
