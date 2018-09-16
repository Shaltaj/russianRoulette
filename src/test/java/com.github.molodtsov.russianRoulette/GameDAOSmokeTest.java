package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.dao.GameDAOImpl;
import com.github.molodtsov.russianRoulette.dao.PlayerDAOImpl;
import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class GameDAOSmokeTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private PlayerDAOImpl playerDAO;
    @Autowired
    private GameDAOImpl gameDAO;

    //updateGame
    @Test
    public void updateGameTest(){
        Game game = new Game();
        gameDAO.updateGame(game);
        Assert.assertEquals(game, em.find(Game.class, game.getId()));

    }

    //currentGame
    @Test
    public void currentGameSuccessTest(){
        Player player = playerDAO.registerPlayer("name", "login", "password");

         Game game = new Game(player);
        gameDAO.updateGame(game);

        Assert.assertEquals(game, gameDAO.currentGame(player));
    }

    @Test
    public void currentGameClosedTest(){
        Player player = playerDAO.registerPlayer("name", "login", "password");

        Game game = new Game(player);
        game.setGameClosed(true);
        gameDAO.updateGame(game);

        Assert.assertNull(gameDAO.currentGame(player));

    }

    @Test
    public void currentGameEmptyTest(){
        Player player = playerDAO.registerPlayer("name", "login", "password");



        Assert.assertNull(gameDAO.currentGame(player));

    }


    //hostGame
    @Test
    public void hostGameSuccessTest(){
        Player player = playerDAO.registerPlayer("name", "login", "password");

        Game game = gameDAO.hostGame(player);
        Assert.assertEquals(game, gameDAO.currentGame(player));

    }

    @Test (expected = IllegalArgumentException.class)
    public void hostGameDuplicateTest(){
        Player player = playerDAO.registerPlayer("name", "login", "password");

        gameDAO.hostGame(player);
        gameDAO.hostGame(player);
    }

    //joinGame
    @Test
    public void joinGameSuccessTest(){

        Player player1 = playerDAO.registerPlayer("name", "login", "password");
        Game game = gameDAO.hostGame(player1);
        Player player2 = playerDAO.registerPlayer("name2", "login2", "password2");
        gameDAO.joinGame(game, player2);

        Assert.assertEquals(game, gameDAO.currentGame(player2));

    }

    @Test (expected = IllegalArgumentException.class)
    public void joinGameDuplicateTest(){

        Player player = playerDAO.registerPlayer("name", "login", "password");
        Game game = gameDAO.hostGame(player);
        gameDAO.joinGame(game, player);
    }

    //findGames
    @Test
    public void findGamesListTest() {
        for (int i = 1; i <= 20; i++) {
            Player player = playerDAO.registerPlayer("name"+i, "login"+i, "password"+i);
            Game game = gameDAO.hostGame(player);
            if (i>10) {
                game.setGameClosed(true);
                gameDAO.updateGame(game);
            }

        }
        List<Game> listGame = gameDAO.findGames(10);
        Assert.assertEquals(10, listGame.size());

        listGame = gameDAO.findGames(9);
        Assert.assertEquals(9, listGame.size());

        listGame = gameDAO.findGames(11);
        Assert.assertEquals(10, listGame.size());

    }

    @Test //(expected = EntityExistsException.class)
    public void findGamesEmptyListTest() {
        List<Game> listGame = gameDAO.findGames(10);
        Assert.assertEquals(0, listGame.size());
    }

}
