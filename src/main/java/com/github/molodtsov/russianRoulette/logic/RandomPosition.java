package com.github.molodtsov.russianRoulette.logic;

import java.util.OptionalInt;
import java.util.Random;

public class RandomPosition {

    public static int GetRandomPosition() {

        Random r = new Random();
        OptionalInt oi = r.ints(1, 7).findFirst();

        return oi.isPresent() ? oi.getAsInt() : 1;

    }
}
