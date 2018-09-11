package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.dao.PlayerDAO;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlayerDAOSmokeTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private PlayerDAO playerDAO;

    @Test
    public void UpdatePlayerTest() {

        Player player = new Player("name1", "login1", "password1");
        playerDAO.UpdatePlayer(player);

        Assert.assertEquals(player, em.find(Player.class, "login1"));
    }

    @Test
    public void SignInTestSuccess() {

        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        Player playerSI = playerDAO.SignInPlayer("login", "password");
        Assert.assertEquals(player, playerSI);

    }

    @Test
    public void SignInTestWrongLogin() {

        playerDAO.RegisterPlayer("name", "login", "password");
        Player playerSI = playerDAO.SignInPlayer("login1", "password");
        Assert.assertNull(playerSI);

    }

    @Test
    public void SignInTestWrongPassword() {

        playerDAO.RegisterPlayer("name", "login", "password");
        Player playerSI = playerDAO.SignInPlayer("login", "password1");
        Assert.assertNull(playerSI);

    }

    @Test
    public void RegisterPlayerTestNew() {

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

        playerDAO.RegisterPlayer("name", "login", "password");
        playerDAO.RegisterPlayer("name1", "login", "password1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void RegisterPlayerTestEmptyName() {

        playerDAO.RegisterPlayer("", "login", "password");
    }

    @Test (expected = IllegalArgumentException.class)
    public void RegisterPlayerTestEmptyLogin() {

        playerDAO.RegisterPlayer("name", "", "password");
    }

    @Test (expected = IllegalArgumentException.class)
    public void RegisterPlayerTestEmptyPassword() {

        playerDAO.RegisterPlayer("name", "login", "");
    }

    @Test
    public void TopPlayersListTest() {

        for (int i = 1; i <= 10; i++) {
            playerDAO.RegisterPlayer("name"+i, "login"+i, "password"+i);
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

}
