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
import java.util.*;

public class PlayerDAOSmokeTest {
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
        playerDAO.UpdatePlayer(player);

        Assert.assertEquals(player, em.find(Player.class, "login1"));
    }

    @Test
    public void SignInTestSuccess() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        Player playerSI = playerDAO.SignInPlayer("login", "password");
        Assert.assertEquals(player, playerSI);

    }

    @Test
    public void SignInTestWrongLogin() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        Player playerSI = playerDAO.SignInPlayer("login1", "password");
        Assert.assertNull(playerSI);

    }

    @Test
    public void SignInTestWrongPassword() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        Player playerSI = playerDAO.SignInPlayer("login", "password1");
        Assert.assertNull(playerSI);

    }

    @Test
    public void RegisterPlayerTestNew() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        player.setMoney(1);
        player.setLose(2);
        player.setWin(3);
        playerDAO.UpdatePlayer(player);
        Player plPersist = em.find(Player.class, "login");

        Assert.assertEquals(player, plPersist);
        Assert.assertEquals("name", plPersist.getName());
        Assert.assertEquals("login", plPersist.getLogin());
        Assert.assertEquals("password", plPersist.getPassword());
        Assert.assertEquals(1, plPersist.getMoney());
        Assert.assertEquals(2, plPersist.getLose());
        Assert.assertEquals(3, plPersist.getWin());
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

    @Test
    public void TopPlayersListTest() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        for (int i = 1; i <= 10; i++) {
            Player player = playerDAO.RegisterPlayer("name"+i, "login"+i, "password"+i);
        }
        List<Player> plListPersist = playerDAO.TopPlayers(10);
        Assert.assertEquals(10, plListPersist.size());

        plListPersist = playerDAO.TopPlayers(9);
        Assert.assertEquals(9, plListPersist.size());

        plListPersist = playerDAO.TopPlayers(11);
        Assert.assertEquals(10, plListPersist.size());

    }

    @Test
    public void TopPlayersOrderTest() {
        PlayerDAO playerDAO = new PlayerDAOImpl(em);
        List<Player> playerList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Player player = playerDAO.RegisterPlayer("name"+i, "login"+i, "password"+i);
            player.setMoney(i*10);
            playerDAO.UpdatePlayer(player);

            playerList.add(player);
        }
        List<Player> plListPersist = playerDAO.TopPlayers(10);
        for (int i = 1; i <= 10; i++) {
            Assert.assertEquals(playerList.get(i-1), plListPersist.get(10-i));
        }


    }

    //[TODO] JoinGame

    //[TODO] CurrentGame

    //[TODO] FindGames

    //[TODO] Add game
}
