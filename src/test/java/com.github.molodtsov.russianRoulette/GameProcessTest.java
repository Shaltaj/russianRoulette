package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.logic.GameProcess;
import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameProcessTest {
    @Test
    public void testSuccessGameProcess() {
        Player player1 = new Player("name1", "login1", "password1");
        Player player2 = new Player("name2", "login2", "password2");

        //How can i test in junit IllegalArgumentException?
        //https://stackoverflow.com/questions/19217588/illegalargumentexception-class-as-expected-in-junit

        //[TODO] Test when Player1 is empty
        //[TODO] Test when Player2 is empty
        //[TODO] Test when Player2=Player1 is empty

        Game game = new Game(player1);
        game.setPlayer2(player2);

        GameProcess gameProcess = new GameProcess(game);
        gameProcess.Proceed();

        Assert.assertNotNull(game.getWinner());
        Assert.assertNotNull(game.getLooser());
        Assert.assertNotSame("log is empty", "", game.getLog());
        Assert.assertTrue("Game is still open", game.getGameClosed());
    }

    //How can i test in junit IllegalArgumentException?
    //https://stackoverflow.com/questions/19217588/illegalargumentexception-class-as-expected-in-junit

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyPlayer1GameProcess() {
        Game game = new Game();
        GameProcess gameProcess = new GameProcess(game);
        gameProcess.Proceed();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyPlayer2GameProcess() {
        Player player1 = new Player("name1", "login1", "password1");
        Game game = new Game(player1);
        GameProcess gameProcess = new GameProcess(game);
        gameProcess.Proceed();
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSamePlayersGameProcess() {
        Player player1 = new Player("name1", "login1", "password1");
        Game game = new Game(player1);
        game.setPlayer2(player1);
        GameProcess gameProcess = new GameProcess(game);
        gameProcess.Proceed();
    }


}
