package com.example.johan.ssbk;

import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


/**
 * Created by Johan on 2015-03-08.
 */
public class Cats {

    static int number;

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

    public static void showCat(ImageButton view){
        view.setImageResource(R.drawable.box_cat_normal);
    }

    public static void hideCat(ImageButton view){
        view.setImageResource(R.drawable.box_closed);
    }
}
