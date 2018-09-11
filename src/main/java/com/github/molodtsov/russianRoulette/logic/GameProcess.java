package com.github.molodtsov.russianRoulette.logic;

import com.github.molodtsov.russianRoulette.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameProcess {
    private Game game;

    @Autowired
    public GameProcess(Game game) {
        this.game = game;
    }

    public void Proceed() {
        if (game.getPlayer1() == null) {
            throw new IllegalArgumentException("Player1 shouldn't be empty");
        }
        if (game.getPlayer2() == null) {
            throw new IllegalArgumentException("Player2 shouldn't be empty");
        }
        if (game.getPlayer1() == game.getPlayer2()) {
            throw new IllegalArgumentException("Player1 shouldn't be Player2");
        }
        if (game.getBulletPosition() < 1 || game.getBulletPosition() > 6) {
            throw new IllegalArgumentException("Wrong bullet position " + game.getBulletPosition());
        }

        int curPlayerInd = 0;
        game.setLog("bullet position is "+game.getBulletPosition());
        while (!game.getGameClosed()) {
            int curPosition = RandomPosition.GetRandomPosition();
            game.setLog(game.getLog().concat(System.lineSeparator())
                    + "Current Player is " + (curPlayerInd == 0 ? game.getPlayer1() : game.getPlayer2())
                    + ", current position is "+curPosition);
            if (curPosition == game.getBulletPosition()) {
                game.setWinner(curPlayerInd == 0 ? game.getPlayer2() : game.getPlayer1());
                game.setLooser(curPlayerInd == 0 ? game.getPlayer1() : game.getPlayer2());
                game.setGameClosed(true);
                game.getWinner().setMoney(game.getWinner().getMoney()+game.getLooser().getMoney());
                game.getLooser().setMoney(10);
                game.getWinner().addWin();
                game.getLooser().addLose();
            }
            else {
                curPlayerInd = (curPlayerInd + 1) % 2;

            }

        }

    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
