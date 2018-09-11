package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.dao.GameDAOImpl;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;
import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import java.util.List;

@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameDAOSmokeTest {
    @Autowired
    private EntityManager em;

    //UpdateGame
    @Test
    public void UpdateGameTest(){
        GameDAOImpl gameDAO = new GameDAOImpl();
        Game game = new Game();
        gameDAO.UpdateGame(game);
        Assert.assertEquals(game, em.find(Game.class, game.getId()));

    }

    //CurrentGame
    @Test
    public void CurrentGameSuccessTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        Player player = playerDAO.RegisterPlayer("name", "login", "password");

        GameDAOImpl gameDAO = new GameDAOImpl();
        Game game = new Game(player);
        gameDAO.UpdateGame(game);

        Assert.assertEquals(game, gameDAO.CurrentGame(player));
    }

    @Test
    public void CurrentGameClosedTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        Player player = playerDAO.RegisterPlayer("name", "login", "password");

        GameDAOImpl gameDAO = new GameDAOImpl();
        Game game = new Game(player);
        game.setGameClosed(true);
        gameDAO.UpdateGame(game);

        Assert.assertNull(gameDAO.CurrentGame(player));

    }

    @Test
    public void CurrentGameEmptyTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        Player player = playerDAO.RegisterPlayer("name", "login", "password");

        GameDAOImpl gameDAO = new GameDAOImpl();


        Assert.assertNull(gameDAO.CurrentGame(player));

    }


    //HostGame
    @Test
    public void HostGameSuccessTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        Player player = playerDAO.RegisterPlayer("name", "login", "password");

        GameDAOImpl gameDAO = new GameDAOImpl();
        Game game = gameDAO.HostGame(player);
        Assert.assertEquals(game, gameDAO.CurrentGame(player));

    }

    @Test (expected = IllegalArgumentException.class)
    public void HostGameDuplicateTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        Player player = playerDAO.RegisterPlayer("name", "login", "password");

        GameDAOImpl gameDAO = new GameDAOImpl();
        gameDAO.HostGame(player);
        gameDAO.HostGame(player);
    }

    //JoinGame
    @Test
    public void JoinGameSuccessTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        GameDAOImpl gameDAO = new GameDAOImpl();

        Player player1 = playerDAO.RegisterPlayer("name", "login", "password");
        Game game = gameDAO.HostGame(player1);
        Player player2 = playerDAO.RegisterPlayer("name2", "login2", "password2");
        gameDAO.JoinGame(game, player2);

        Assert.assertEquals(game, gameDAO.CurrentGame(player2));

    }

    @Test (expected = IllegalArgumentException.class)
    public void JoinGameDuplicateTest(){
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        GameDAOImpl gameDAO = new GameDAOImpl();

        Player player = playerDAO.RegisterPlayer("name", "login", "password");
        Game game = gameDAO.HostGame(player);
        gameDAO.JoinGame(game, player);
    }

    //FindGames
    @Test
    public void FindGamesListTest() {
        PlayerDAOImpl playerDAO = new PlayerDAOImpl();
        GameDAOImpl gameDAO = new GameDAOImpl();
        for (int i = 1; i <= 20; i++) {
            Player player = playerDAO.RegisterPlayer("name"+i, "login"+i, "password"+i);
            Game game = gameDAO.HostGame(player);
            if (i>10) {
                game.setGameClosed(true);
                gameDAO.UpdateGame(game);
            }

        }
        List<Game> listGame = gameDAO.FindGames(10);
        Assert.assertEquals(10, listGame.size());

        listGame = gameDAO.FindGames(9);
        Assert.assertEquals(9, listGame.size());

        listGame = gameDAO.FindGames(11);
        Assert.assertEquals(10, listGame.size());

    }

    @Test //(expected = EntityExistsException.class)
    public void FindGamesEmptyListTest() {
        GameDAOImpl gameDAO = new GameDAOImpl();
        List<Game> listGame = gameDAO.FindGames(10);
        Assert.assertEquals(0, listGame.size());
    }

}
