package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOSmokeTest {
    EntityManagerFactory emf;
    EntityManager em;

    @Before
    public void setup() {
        emf = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        em = emf.createEntityManager();

    }

    @After
    public void after() {
        if (em!=null) {
            em.close();
        }
        if (emf!=null) {
            emf.close();
        }

    }

    @Test
    public void playerAdd() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        Player player = new Player("name1", "login1", "password1");
        playerDAO.Add(player);

        Assert.assertEquals(player, em.find(Player.class, "login1"));
    }
}
