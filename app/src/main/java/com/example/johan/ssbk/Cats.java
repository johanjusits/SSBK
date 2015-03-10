package com.example.johan.ssbk;

import android.content.Context;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Created by Johan on 2015-03-08.
 */
public class Cats {

    static int number;
    static int objImg;
    static int normalCatValue = 1;
    static int luckyCatValue = 2;
    static int badCatValue = 1;
    static String normalCat = "box_cat_normal";
    static String luckyCat = "box_cat_lucky";
    static String badCat = "box_cat_bad";
    static String plus = "+";
    static String minus = "-";

    public static int genRand(int number) {
        return new Random().nextInt(number);
    }

    /* RETURNS VALUE IN MILLISECONDS */
    public static int getWaitUntilNext(int time){
        number = genRand(time);
        return number;
    }

    /* RETURNS VALUE FOR LOCATION TO SHOW */
    public static int getLocationToShow(ArrayList activeBoxes){
        int occurrences;
        do{
            number = genRand(16);
            occurrences = Collections.frequency(activeBoxes, number);
        } while (occurrences != 0);
        return number;
    }

    /* RETURNS VALUE FOR LOCATION TO HIDE */
    public static int getLocationToHide(ArrayList activeBoxes){
        int occurrences;
        do{
            number = genRand(16);
            occurrences = Collections.frequency(activeBoxes, number);
        } while (occurrences != 1);
        return number;
    }

    public static void showObj(ImageButton view, String imageName, Context context){
        objImg = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        view.setImageResource(objImg);
    }

    public static void hideObj(ImageButton view){
        view.setImageResource(R.drawable.box_closed);
    }

    public static String getObjImgName(){
        int boxChance = genRand(100);
        if(boxChance <= 10){
            return luckyCat;
        } else if(boxChance >= 11 && boxChance <= 30){
            return badCat;
        } else if(boxChance > 30 ){
            return normalCat;
        }
        return "";
    }

    public static void setObjValue(int location, String objName){
        switch (location) {
            case 0:
                switch (objName){
                case "box_cat_normal":
                    Activity_PlayScreen.obj001Value = normalCatValue;
                    Activity_PlayScreen.plusOrMinus = plus;
                    break;
                case "box_cat_lucky":
                    Activity_PlayScreen.obj001Value = luckyCatValue;
                    Activity_PlayScreen.plusOrMinus = plus;
                    break;
                case "box_cat_bad":
                    Activity_PlayScreen.obj001Value = badCatValue;
                    Activity_PlayScreen.plusOrMinus = minus;
                    break;
            }
            break;
            case 1:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj002Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj002Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj002Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 2:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj003Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj003Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj003Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 3:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj004Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj004Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj004Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 4:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj005Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj005Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj005Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 5:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj006Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj006Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj006Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 6:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj007Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj007Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj007Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 7:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj008Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj008Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj008Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 8:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj009Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj009Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj009Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 9:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj010Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj010Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj010Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 10:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj011Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj011Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj011Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 11:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj012Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj012Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj012Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 12:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj013Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj013Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj013Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 13:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj014Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj014Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj014Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 14:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj015Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj015Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj015Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
            case 15:
                switch (objName){
                    case "box_cat_normal":
                        Activity_PlayScreen.obj016Value = normalCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_lucky":
                        Activity_PlayScreen.obj016Value = luckyCatValue;
                        Activity_PlayScreen.plusOrMinus = plus;
                        break;
                    case "box_cat_bad":
                        Activity_PlayScreen.obj016Value = badCatValue;
                        Activity_PlayScreen.plusOrMinus = minus;
                        break;
                }
            break;
        }
    }

}
