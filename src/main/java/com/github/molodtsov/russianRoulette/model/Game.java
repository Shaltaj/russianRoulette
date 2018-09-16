package com.github.molodtsov.russianRoulette.model;

import com.github.molodtsov.russianRoulette.logic.RandomPosition;

import javax.persistence.*;

@Entity(name = "GAMES")
public class Game {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Player player1;
    @OneToOne
    private Player player2;
    @Column
    private int bulletPosition;
    @Column
    private String log;
    @OneToOne
    private Player winner;
    @OneToOne
    private Player looser;
    @Column
    private Boolean gameClosed;

    //CONSTRUCTORS

    public Game(Player player1) {
        this.player1 = player1;
        this.bulletPosition = RandomPosition.getRandomPosition();
        this.gameClosed = false;
        this.log = "";
    }

    public Game() {
    }

    //GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getBulletPosition() {
        return bulletPosition;
    }

    public void setBulletPosition(int bulletPosition) {
        this.bulletPosition = bulletPosition;
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
