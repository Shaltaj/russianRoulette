package com.github.molodtsov.russianRoulette;


import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;
import com.github.molodtsov.russianRoulette.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        try {
            PlayerDAO playerDAO = new PlayerDAOImpl(em);
            Player player = new Player("name1", "login1", "password1");
            playerDAO.Add(player);
        } finally {
            em.close();
            emf.close();
        }
    }
}