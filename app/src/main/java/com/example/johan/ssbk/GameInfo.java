package com.example.johan.ssbk;

import java.util.Random;

/**
 * Created by Johan on 2015-03-09.
 */
public class GameInfo {

    public static int genRand(int number) {
        return new Random().nextInt(number);
    }

    public static String getGameOverMsg(Boolean playerDied, int score){
        if(playerDied){
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
                    return "You gotta be more careful..";
            }
        } else {
            if(score < 20) {
                int chance = genRand(4);
                switch (chance) {
                    case 0:
                        return "What went wrong?";
                    case 1:
                        return "Quite embarrassing..";
                    case 2:
                        return "Ouch!";
                    case 3:
                        return "Noob.";
                }
            }
            if(score >= 20 && score < 31) {
                int chance = genRand(3);
                switch (chance) {
                    case 0:
                        return "Not too shabby!";
                    case 1:
                        return "Getting there..";
                    case 2:
                        return "Not the worst.";
                }
            }
            if(score >= 31 && score < 41) {
                int chance = genRand(3);
                switch (chance) {
                    case 0:
                        return "Cool!";
                    case 1:
                        return "Pretty good!";
                    case 2:
                        return "Totally fine!";
                }
            }
            if(score >= 41 && score < 51) {
                int chance = genRand(3);
                switch (chance) {
                    case 0:
                        return "Impressive!";
                    case 1:
                        return "Good job!";
                    case 2:
                        return "Great work!";
                }
            }
            if(score >= 51 && score < 61) {
                int chance = genRand(3);
                switch (chance) {
                    case 0:
                        return "Ace.";
                    case 1:
                        return "Fantastic!";
                    case 2:
                        return "Excellent!";
                }
            }
            if(score >= 61) {
                int chance = genRand(3);
                switch (chance) {
                    case 0:
                        return "Wow, are your fingers on fire?";
                    case 1:
                        return "Can I have your autograph?";
                    case 2:
                        return "Almost perfect!";
                }
            }
        }
        return "";
    }

}
