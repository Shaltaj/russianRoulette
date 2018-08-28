package com.github.molodtsov.russianRoulette;

import com.github.molodtsov.russianRoulette.logic.RandomPosition;
import org.junit.Assert;
import org.junit.Test;

public class RandomPositionTest {
    @Test
    public void testGetRandomPosition() {
        int rp = RandomPosition.GetRandomPosition();
        //System.out.println("Random position: "+rp);
        if (rp < 1 || rp > 6) {
            Assert.fail("Random fails instead 1-6 it return " + rp);
        }
    }
}
