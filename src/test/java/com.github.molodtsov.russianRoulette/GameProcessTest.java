package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.logic.GameProcess;
import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class GameProcessTest {
    @Test
    public void testGameProcess() {
        Player player1 = new Player("name1", "login1", "password1");
        Player player2 = new Player("name2", "login2", "password2");

        //[TODO] How can i test in junit IllegalArgumentException?
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
}
