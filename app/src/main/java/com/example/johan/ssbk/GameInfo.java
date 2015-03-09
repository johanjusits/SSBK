package com.example.johan.ssbk;

import java.util.Random;

/**
 * Created by Johan on 2015-03-09.
 */
public class GameInfo {

    public static int genRand(int number) {
        return new Random().nextInt(number);
    }

    public static String getDeathMsg(){
        int chance = genRand(6);
        switch (chance) {
            case 0:
                return "Another one bites the dust..";
            case 1:
                return "A bit too overzealous?";
            case 2:
                return "Aww!";
            case 3:
                return "Too bad...too bad.";
            case 4:
                return "You'll do better next time!";
            case 5:
                return "So close!";
        }
        return "";
    }

}
