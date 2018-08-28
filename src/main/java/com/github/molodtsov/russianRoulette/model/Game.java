package com.github.molodtsov.russianRoulette.model;

import com.github.molodtsov.russianRoulette.logic.RandomPosition;

public class Game {
    private Player player1;
    private Player player2;
    private int bulletPosition;
    private String log;
    private Player winner;
    private Player looser;
    private Boolean gameClosed;

    public Game(Player player1) {
        this.player1 = player1;
        this.bulletPosition = RandomPosition.GetRandomPosition();
        this.gameClosed = false;
        this.log = "";
    }

    public Game() {
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getBulletPosition() {
        return bulletPosition;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Player getLooser() {
        return looser;
    }

    public void setLooser(Player looser) {
        this.looser = looser;
    }

    public Boolean getGameClosed() {
        return gameClosed;
    }

    public void setGameClosed(Boolean gameClosed) {
        this.gameClosed = gameClosed;
    }


}
