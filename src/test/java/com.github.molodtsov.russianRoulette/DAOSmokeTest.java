package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOSmokeTest {
    private EntityManagerFactory emf;
    private EntityManager em;

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
    public void playerAddTest() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        Player player = new Player("name1", "login1", "password1");
        playerDAO.Add(player);

        Assert.assertEquals(player, em.find(Player.class, "login1"));
    }

    //RegisterPlayer
    @Test
    public void RegisterPlayerTestNew() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        //Player new Player("name1", "login1", "password1");
        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        Player plPersist = em.find(Player.class, "login");

        Assert.assertEquals(player, plPersist);
        Assert.assertEquals("name", plPersist.getName());
        Assert.assertEquals("login", plPersist.getLogin());
        Assert.assertEquals("password", plPersist.getPassword());
    }

    @Test (expected = EntityExistsException.class)
    public void RegisterPlayerTestDuplicate() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        playerDAO.RegisterPlayer("name", "login", "password");
        playerDAO.RegisterPlayer("name1", "login", "password1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void RegisterPlayerTestEmptyName() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        playerDAO.RegisterPlayer("", "login", "password");
    }

    @Test (expected = IllegalArgumentException.class)
    public void RegisterPlayerTestEmptyLogin() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        playerDAO.RegisterPlayer("name", "", "password");
    }

    @Test (expected = IllegalArgumentException.class)
    public void RegisterPlayerTestEmptyPassword() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        playerDAO.RegisterPlayer("name", "login", "");
    }

    //[TODO] TopPlayers

    //[TODO] JoinGame

    //[TODO] CurrentGame

    //[TODO] FindGames

    //[TODO] Add game
}
