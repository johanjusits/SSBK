package com.example.johan.ssbk;

import android.content.Context;
import android.os.Handler;
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
    static int badCatValue = 5;
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

    public static void hideObj(final ImageButton view){
        view.setImageResource(R.drawable.box_closed);
    }

    public static String getObjImgName(){
        int boxChance = genRand(100);
        if(boxChance <= 5){
            return luckyCat;
        } else if(boxChance >= 6 && boxChance <= 20){
            return badCat;
        } else if(boxChance > 20 ){
            return normalCat;
        }
        return "";
    }

    public static void setObjValue(int location, String objName){
        switch (location) {
            case 0:
                Activity_PlayScreen.obj001Image = objName;
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
                Activity_PlayScreen.obj002Image = objName;
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
                Activity_PlayScreen.obj003Image = objName;
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
                Activity_PlayScreen.obj004Image = objName;
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
                Activity_PlayScreen.obj005Image = objName;
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
                Activity_PlayScreen.obj006Image = objName;
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
                Activity_PlayScreen.obj007Image = objName;
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
                Activity_PlayScreen.obj008Image = objName;
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
                Activity_PlayScreen.obj009Image = objName;
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
                Activity_PlayScreen.obj010Image = objName;
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
                Activity_PlayScreen.obj011Image = objName;
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
                Activity_PlayScreen.obj012Image = objName;
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
                Activity_PlayScreen.obj013Image = objName;
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
                Activity_PlayScreen.obj014Image = objName;
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
                Activity_PlayScreen.obj015Image = objName;
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
                Activity_PlayScreen.obj016Image = objName;
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
