package com.example.johan.ssbk;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by Johan on 2015-03-07.
 */
public class Activity_PlayScreen extends Activity implements View.OnClickListener {

    ImageButton ibBox001, ibBox002, ibBox003, ibBox004, ibBox005, ibBox006, ibBox007, ibBox008,
            ibBox009, ibBox010, ibBox011, ibBox012, ibBox013, ibBox014, ibBox015, ibBox016;
    ViewGroup vgBoxes;
    ProgressBar lifeBar;
    Context context;
    Activity activity;
    Handler handler001;
    Typeface font, boopFont;
    Dialog dialog;
    String fontName = "coolvetica.ttf";
    String boopMsg = "BOOP!";
    String missMsg = "MISS!";
    TextView tvTimeLeft, tvScoreTitle, tvScore, tvBoop;
    Boolean updateTime = false;
    Animation ani_bounce;
    int score = 0;
    int totalActiveBoxes = 0;
    int closePercent = 0;
    int closeNr = 0;
    int openPercent = 0;
    int openNr = 0;
    int hp = 100;
    int objID;
    int boopColor;
    int missColor;
    public static int obj001Value = 0, obj002Value = 0, obj003Value = 0, obj004Value = 0, obj005Value = 0, obj006Value = 0, obj007Value = 0, obj008Value = 0,
            obj009Value = 0, obj010Value = 0, obj011Value = 0, obj012Value = 0, obj013Value = 0, obj014Value = 0, obj015Value = 0, obj016Value = 0;
    long tillNext;
    boolean timerIsRunning = false;
    boolean zeroHp = false;
    boolean hit = false;
    String deathMsg;
    String textBoopBlue = "textBoopBlue";
    String textWhite = "textWhite";
    public static String plusOrMinus = "+";
    ArrayList<Integer> activeObjects = new ArrayList<>();

    private final long startTime = 59000;
    private final long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newplayscreen);

        context = Activity_PlayScreen.this;
        activity = Activity_PlayScreen.this;

        // ADDING DEFAULT VALUES TO THE OBJECTS ARRAY
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);
        activeObjects.add(-1);

        declareImages();

        // TEXT COLORS
        boopColor = context.getResources().getIdentifier(textBoopBlue, "color", getPackageName());
        missColor = context.getResources().getIdentifier(textWhite, "color", getPackageName());

        // ANIMATIONS
        ani_bounce = AnimationUtils.loadAnimation(this, R.anim.ani_bounce);

        // DEATH MESSAGE AND OBJECTS SETUP
        deathMsg = GameInfo.getDeathMsg();
        vgBoxes = (ViewGroup) findViewById(R.id.rootLayout);
        disable(vgBoxes);

        // FONT
        font = Typeface.createFromAsset(getAssets(), fontName);

        // LIFE PROGRESS BAR
        lifeBar = (ProgressBar) findViewById(R.id.pbLifeBar);
        Drawable draw = getResources().getDrawable(R.drawable.progressbar);
        lifeBar.setProgressDrawable(draw);

        // TEXTVIEWS
        tvTimeLeft = (TextView) findViewById(R.id.tvTimeLeft);
        tvScoreTitle = (TextView) findViewById(R.id.tvScoreTitle);
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvBoop = (TextView) findViewById(R.id.tvBoop);

        tvTimeLeft.setTypeface(font);
        tvScoreTitle.setTypeface(font);
        tvScore.setTypeface(font);
        tvBoop.setTypeface(font);
        tvBoop.bringToFront();

        tvScore.setText(String.valueOf(score));

        tvTimeLeft.setText("59");

        handler001 = new Handler();

        initiateBoard();
    }

    private void initiateBoard(){
        handler001.postDelayed(new Runnable() {
            public void run() {
                dialog = DialogWindows.gameStartWindow("GAME STARTS IN", context, activity, font, handler001);
                dialog.show();
            }
        }, 1500);

        handler001.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 7500);

        handler001.postDelayed(new Runnable() {
            public void run() {
                CountDown counter = new CountDown(startTime, interval);
                counter.start();
                timerIsRunning = true;
                enable(vgBoxes);
                pendulumA();
            }
        }, 8500);
    }

    private void pendulumA(){
        tillNext = Cats.getWaitUntilNext(1500);
        setOpenBoxNr();
        handler001.postDelayed(new Runnable() {
            public void run() {
                if(totalActiveBoxes < 16 && openNr == 1){
                    showBox();
                }
                if(totalActiveBoxes < 15 && openNr == 2){
                    showBox();
                    showBox();
                }
            }
        }, tillNext);
        handler001.postDelayed(new Runnable() {
            public void run() {
                if(timerIsRunning){
                    pendulumB();
                }
            }
        }, tillNext + 500);
    }

    private void pendulumB(){
        tillNext = Cats.getWaitUntilNext(1500);
        setOpenBoxNr();
        handler001.postDelayed(new Runnable() {
            public void run() {
                if(totalActiveBoxes < 16 && openNr == 1){
                    showBox();
                }
                if(totalActiveBoxes < 15 && openNr == 2){
                    showBox();
                    showBox();
                }
            }
        }, tillNext);
        handler001.postDelayed(new Runnable() {
            public void run() {
                if(timerIsRunning){
                    pendulumC();
                }
            }
        }, tillNext + 500);
    }

    /* THIS PENDULUM DEALS WITH CLOSING BOXES */
    private void pendulumC(){
        tillNext = Cats.getWaitUntilNext(1500);
        if(totalActiveBoxes > 0) {
            int closeChance = Cats.genRand(100);
            if (totalActiveBoxes == 1){
                closePercent = 50;
                closeNr = 1;
            }
            if (totalActiveBoxes == 2){
                closePercent = 30;
                closeNr = 1;
            }
            if (totalActiveBoxes == 3){
                closePercent = 25;
                closeNr = 2;
            }
            if (totalActiveBoxes == 4){
                closePercent = 10;
                closeNr = 2;
            }
            if (totalActiveBoxes > 4){
                closePercent = 10;
                closeNr = 3;
            }
            if (closeChance >= closePercent && closeNr == 1) {
                hideBox();
            }
            if (closeChance >= closePercent && closeNr == 2) {
                hideBox();
                hideBox();
            }
            if (closeChance >= closePercent && closeNr == 3) {
                hideBox();
                hideBox();
                hideBox();
            }
        }

        handler001.postDelayed(new Runnable() {
            public void run() {
                if(timerIsRunning){
                    pendulumA();
                }
            }
        }, tillNext + 500);
    }

    private void hitSuccesful(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibObj001:
                hit = hitOrMiss(0);
                objID = ibBox001.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox001);
                    int index = getIndexToClear(0);
                    activeObjects.set(index, -1);
                    updateScore(obj001Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj002:
                hit = hitOrMiss(1);
                objID = ibBox002.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox002);
                    int index = getIndexToClear(1);
                    activeObjects.set(index, -1);
                    updateScore(obj002Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj003:
                hit = hitOrMiss(2);
                objID = ibBox003.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox003);
                    int index = getIndexToClear(2);
                    activeObjects.set(index, -1);
                    updateScore(obj003Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj004:
                hit = hitOrMiss(3);
                objID = ibBox004.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox004);
                    int index = getIndexToClear(3);
                    activeObjects.set(index, -1);
                    updateScore(obj004Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj005:
                hit = hitOrMiss(4);
                objID = ibBox005.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox005);
                    int index = getIndexToClear(4);
                    activeObjects.set(index, -1);
                    updateScore(obj005Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj006:
                hit = hitOrMiss(5);
                objID = ibBox006.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox006);
                    int index = getIndexToClear(5);
                    activeObjects.set(index, -1);
                    updateScore(obj006Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj007:
                hit = hitOrMiss(6);
                objID = ibBox007.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox007);
                    int index = getIndexToClear(6);
                    activeObjects.set(index, -1);
                    updateScore(obj007Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj008:
                hit = hitOrMiss(7);
                objID = ibBox008.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox008);
                    int index = getIndexToClear(7);
                    activeObjects.set(index, -1);
                    updateScore(obj008Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj009:
                hit = hitOrMiss(8);
                objID = ibBox009.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox009);
                    int index = getIndexToClear(8);
                    activeObjects.set(index, -1);
                    updateScore(obj009Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj010:
                hit = hitOrMiss(9);
                objID = ibBox010.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox010);
                    int index = getIndexToClear(9);
                    activeObjects.set(index, -1);
                    updateScore(obj010Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj011:
                hit = hitOrMiss(10);
                objID = ibBox011.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox011);
                    int index = getIndexToClear(10);
                    activeObjects.set(index, -1);
                    updateScore(obj011Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj012:
                hit = hitOrMiss(11);
                objID = ibBox012.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox012);
                    int index = getIndexToClear(11);
                    activeObjects.set(index, -1);
                    updateScore(obj012Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj013:
                hit = hitOrMiss(12);
                objID = ibBox013.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox013);
                    int index = getIndexToClear(12);
                    activeObjects.set(index, -1);
                    updateScore(obj013Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj014:
                hit = hitOrMiss(13);
                objID = ibBox014.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox014);
                    int index = getIndexToClear(13);
                    activeObjects.set(index, -1);
                    updateScore(obj014Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj015:
                hit = hitOrMiss(14);
                objID = ibBox015.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox015);
                    int index = getIndexToClear(14);
                    activeObjects.set(index, -1);
                    updateScore(obj015Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
            case R.id.ibObj016:
                hit = hitOrMiss(15);
                objID = ibBox016.getId();
                if (hit){
                    animateMsg(objID, boopMsg, boopColor);
                    totalActiveBoxes--;
                    Cats.hideObj(ibBox016);
                    int index = getIndexToClear(15);
                    activeObjects.set(index, -1);
                    updateScore(obj016Value, plusOrMinus);
                } else {
                    animateMsg(objID, missMsg, missColor);
                    updateScore(1, "-");
                }
                break;
        }
    }

    public void updateTimer(long newStartTime, long newInterval){
        CountDown counter = new CountDown(newStartTime, newInterval);
        counter.start();
    }

    public ImageButton setViewToSend(int location, String showOrHide){
        switch (location) {
            case 0:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(0, 0);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(0, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox001;
            case 1:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(1, 1);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(1, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox002;
            case 2:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(2, 2);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(2, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox003;
            case 3:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(3, 3);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(3, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox004;
            case 4:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(4, 4);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(4, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox005;
            case 5:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(5, 5);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(5, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox006;
            case 6:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(6, 6);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(6, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox007;
            case 7:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(7, 7);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(7, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox008;
            case 8:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(8, 8);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(8, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox009;
            case 9:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(9, 9);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(9, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox010;
            case 10:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(10, 10);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(10, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox011;
            case 11:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(11, 11);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(11, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox012;
            case 12:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(12, 12);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(12, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox013;
            case 13:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(13, 13);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(13, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox014;
            case 14:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(14, 14);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(14, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox015;
            case 15:
                if (!activeObjects.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeObjects.set(15, 15);
                        totalActiveBoxes++;
                    } else {
                        activeObjects.set(15, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox016;
        }
        return ibBox001;
    }

    private void declareImages(){
        ibBox001 = (ImageButton) findViewById(R.id.ibObj001);
        ibBox001.setOnClickListener(this);
        ibBox002 = (ImageButton) findViewById(R.id.ibObj002);
        ibBox002.setOnClickListener(this);
        ibBox003 = (ImageButton) findViewById(R.id.ibObj003);
        ibBox003.setOnClickListener(this);
        ibBox004 = (ImageButton) findViewById(R.id.ibObj004);
        ibBox004.setOnClickListener(this);
        ibBox005 = (ImageButton) findViewById(R.id.ibObj005);
        ibBox005.setOnClickListener(this);
        ibBox006 = (ImageButton) findViewById(R.id.ibObj006);
        ibBox006.setOnClickListener(this);
        ibBox007 = (ImageButton) findViewById(R.id.ibObj007);
        ibBox007.setOnClickListener(this);
        ibBox008 = (ImageButton) findViewById(R.id.ibObj008);
        ibBox008.setOnClickListener(this);
        ibBox009 = (ImageButton) findViewById(R.id.ibObj009);
        ibBox009.setOnClickListener(this);
        ibBox010 = (ImageButton) findViewById(R.id.ibObj010);
        ibBox010.setOnClickListener(this);
        ibBox011 = (ImageButton) findViewById(R.id.ibObj011);
        ibBox011.setOnClickListener(this);
        ibBox012 = (ImageButton) findViewById(R.id.ibObj012);
        ibBox012.setOnClickListener(this);
        ibBox013 = (ImageButton) findViewById(R.id.ibObj013);
        ibBox013.setOnClickListener(this);
        ibBox014 = (ImageButton) findViewById(R.id.ibObj014);
        ibBox014.setOnClickListener(this);
        ibBox015 = (ImageButton) findViewById(R.id.ibObj015);
        ibBox015.setOnClickListener(this);
        ibBox016 = (ImageButton) findViewById(R.id.ibObj016);
        ibBox016.setOnClickListener(this);
    }

    public int getIndexToClear(int boxNr){
        return activeObjects.indexOf(boxNr);
    }

    private void updateScore(int value, String sign){
        if(sign.equals("-")){
            score = score - value;
            tvScore.setText(String.valueOf(score));
            int progress = lifeBar.getProgress();
            hp = hp - 20;
            ObjectAnimator animation = ObjectAnimator.ofInt(lifeBar, "progress", progress - 20);
            animation.setDuration(500);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
            if(hp <= 0){
                zeroHp = true;
            }
        } else {
            score = score + value;
            tvScore.setText(String.valueOf(score));
        }

    }

    public boolean hitOrMiss(int boxNr){
        int occurrences = Collections.frequency(activeObjects, boxNr);
        return occurrences > 0;
    }

    private void animateMsg(int objId, String msg, int color) /* + int margin */{
        tvBoop.setVisibility(View.VISIBLE);
        tvBoop.setText(msg);
        tvBoop.setTextColor(getResources().getColor(color));

        RelativeLayout.LayoutParams objMsgSettings = (RelativeLayout.LayoutParams) tvBoop.getLayoutParams();
        objMsgSettings.addRule(RelativeLayout.ABOVE, objId);

        objMsgSettings.addRule(RelativeLayout.ALIGN_LEFT, objId);
        objMsgSettings.addRule(RelativeLayout.ALIGN_RIGHT, 0);
        //objMsgSettings.setMargins(margin, 0, 0 , 0);

        tvBoop.setLayoutParams(objMsgSettings);
        tvBoop.startAnimation(ani_bounce);
    }

    private void hideBox(){
        String showOrHide = "Hide";
        int locationToHide = Cats.getLocationToHide(activeObjects);
        ImageButton viewToHide = setViewToSend(locationToHide, showOrHide);
        Cats.hideObj(viewToHide);
    }

    private void showBox(){
        String showOrHide = "Show";
        String objToShow = Cats.getObjImgName();
        int locationToShow = Cats.getLocationToShow(activeObjects);
        ImageButton viewToShow = setViewToSend(locationToShow, showOrHide);
        Cats.setObjValue(locationToShow, objToShow);
        Cats.showObj(viewToShow, objToShow, context);
    }

    private void setOpenBoxNr(){
        if(totalActiveBoxes < 5){
            openPercent = Cats.genRand(100);
            if (openPercent > 65){
                openNr = 2;
            } else {
                openNr = 1;
            }
        } else {
            openNr = 1;
        }
    }

    /* DISABLES THE INTERFACE, PREVENTING PLAYER FROM CLICKING THE BOXES */
    private static void disable(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                disable((ViewGroup) child);
            } else {
                child.setEnabled(false);
            }
        }

    }

    /* ENABLES THE INTERFACE */
    private static void enable(ViewGroup layout) {
        layout.setEnabled(false);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                enable((ViewGroup) child);
            } else {
                child.setEnabled(true);
            }
        }

    }

    public class CountDown extends CountDownTimer {

        public CountDown(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if(zeroHp){
                dialog = DialogWindows.youDiedWindow("YOU DIED!", context, activity, "Restart", font, "Restart", "Exit", deathMsg);
                dialog.show();
                timerIsRunning = false;
                disable(vgBoxes);
                cancel();
            }
            if(updateTime){
                long newTime = millisUntilFinished + 5000;
                long interval = 1000;
                cancel();
                if(newTime > 59000){
                    newTime = 59000;
                }
                updateTimer(newTime, interval);
            }
            tvTimeLeft.setText("" + String.format("%d",
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            updateTime = false;
        }

        @Override
        public void onFinish() {
            tvTimeLeft.setText("Time's up!");
            timerIsRunning = false;
            disable(vgBoxes);
        }
    }

    //PRINTING THE ARRAY LIST
    /*System.out.println("Array Content Pendulum C");
        for (int i = 0; i < activeObjects.size(); i++) {
            System.out.println(activeObjects.get(i));
        }*/

}
