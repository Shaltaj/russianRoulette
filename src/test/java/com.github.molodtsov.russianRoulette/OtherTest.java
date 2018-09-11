package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.model.Game;
import com.github.molodtsov.russianRoulette.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class OtherTest {

    @Test
    public void PlayerTest() {
        Player player = new Player();
        player.setName("name");
        player.setLogin("login");
        player.setPassword("password");
        Assert.assertEquals("name", player.getName());
        Assert.assertEquals("login", player.getLogin());
        Assert.assertEquals("password", player.getPassword());

    }

    @Test
    public void GameTest() {
        Game game = new Game();
        game.setId(1);
        game.setPlayer1(new Player());
        Assert.assertEquals(1, game.getId());
    }

}
