package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.logic.GameProcess;
import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameProcessTest {
    @Test
    public void GameProcessSuccessTest() {
        Player player1 = new Player("name1", "login1", "password1");
        Player player2 = new Player("name2", "login2", "password2");

        Game game = new Game(player1);
        game.setPlayer2(player2);

        GameProcess gameProcess = new GameProcess(game);
        gameProcess.proceed();

        Assert.assertNotNull(game.getWinner());
        Assert.assertNotNull(game.getLooser());
        Assert.assertNotSame("log is empty", "", game.getLog());
        Assert.assertTrue("Game is still open", game.getGameClosed());
    }

    //How can i test in junit IllegalArgumentException?
    //https://stackoverflow.com/questions/19217588/illegalargumentexception-class-as-expected-in-junit

    @Test (expected = IllegalArgumentException.class)
    public void GameProcessEmptyPlayer1Test() {
        Game game = new Game();
        GameProcess gameProcess = new GameProcess(game);
        gameProcess.proceed();
    }

    @Test (expected = IllegalArgumentException.class)
    public void GameProcessEmptyPlayer2Test() {
        Player player1 = new Player("name1", "login1", "password1");
        Game game = new Game(player1);
        GameProcess gameProcess = new GameProcess(game);
        gameProcess.proceed();
    }

    @Test (expected = IllegalArgumentException.class)
    public void GameProcessSamePlayersTest() {
        Player player1 = new Player("name1", "login1", "password1");
        Game game = new Game(player1);
        game.setPlayer2(player1);
        GameProcess gameProcess = new GameProcess(game);
        gameProcess.proceed();
    }

    @Test (expected = IllegalArgumentException.class)
    public void GameProcessBulletPositionTest() {
        Player player1 = new Player("name1", "login1", "password1");
        Player player2 = new Player("name2", "login2", "password2");

        Game game = new Game(player1);
        game.setPlayer2(player2);

        game.setBulletPosition(-1);

        GameProcess gameProcess = new GameProcess(game);
        gameProcess.proceed();
    }

    @Test
    public void GameProcessGetSetTest() {
        Game game1 = new Game();
        GameProcess gameProcess = new GameProcess(game1);
        Game game2 = new Game();
        gameProcess.setGame(game2);
        Assert.assertEquals(gameProcess.getGame(), game2);
    }

}
