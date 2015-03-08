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
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Created by Johan on 2015-03-07.
 */
public class Activity_PlayScreen extends Activity implements View.OnClickListener {

    ImageButton ibBox001, ibBox002, ibBox003, ibBox004, ibBox005, ibBox006, ibBox007, ibBox008,
            ibBox009, ibBox010, ibBox011, ibBox012, ibBox013, ibBox014, ibBox015, ibBox016;
    Toast toast;
    ProgressBar lifeBar;
    Context context;
    Activity activity;
    Handler handler001;
    Typeface font;
    Dialog dialog;
    String fontName = "coolvetica.ttf";
    TextView tvTimeLeft, tvScoreTitle, tvScore;
    Boolean updateTime = false;
    int catValue = 0;
    int score = 0;
    int touchX;
    int touchY;
    int totalActiveBoxes = 0;
    int closePercent = 0;
    int closeNr = 0;
    int openPercent = 0;
    int openNr = 0;
    long tillNext;
    boolean timerIsRunning = false;
    boolean hit = false;
    ArrayList<Integer>activeBoxes = new ArrayList<>();

    private final long startTime = 59000;
    private final long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playscreen);

        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);
        activeBoxes.add(-1);

        declareImages();

        font = Typeface.createFromAsset(getAssets(), fontName);

        lifeBar = (ProgressBar) findViewById(R.id.progressBar);
        Drawable draw = getResources().getDrawable(R.drawable.progressbar);
        lifeBar.setProgressDrawable(draw);

        tvTimeLeft = (TextView) findViewById(R.id.tvTimeLeft);
        tvScoreTitle = (TextView) findViewById(R.id.tvScoreTitle);
        tvScore = (TextView) findViewById(R.id.tvScore);

        tvTimeLeft.setTypeface(font);
        tvScoreTitle.setTypeface(font);
        tvScore.setTypeface(font);

        tvScore.setText(String.valueOf(score));

        tvTimeLeft.setText("59");

        context = Activity_PlayScreen.this;
        activity = Activity_PlayScreen.this;

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
                pendulumA();
            }
        }, 8500);
    }

    private void pendulumA(){
        tillNext = Cats.getWaitUntilNext(1500);
        getOpenBoxNr();
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
        getOpenBoxNr();
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
            System.out.println("Close Chance: " + String.valueOf(closeChance));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibBox001:
                hit = hitOrMiss(0);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox001);
                    int index = getIndexToClear(0);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox002:
                hit = hitOrMiss(1);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox002);
                    int index = getIndexToClear(1);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");;
                }
                break;
            case R.id.ibBox003:
                hit = hitOrMiss(2);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox003);
                    int index = getIndexToClear(2);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox004:
                hit = hitOrMiss(3);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox004);
                    int index = getIndexToClear(3);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox005:
                hit = hitOrMiss(4);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox005);
                    int index = getIndexToClear(4);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox006:
                hit = hitOrMiss(5);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox006);
                    int index = getIndexToClear(5);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox007:
                hit = hitOrMiss(6);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox007);
                    int index = getIndexToClear(6);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox008:
                hit = hitOrMiss(7);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox008);
                    int index = getIndexToClear(7);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox009:
                hit = hitOrMiss(8);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox009);
                    int index = getIndexToClear(8);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox010:
                hit = hitOrMiss(9);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox010);
                    int index = getIndexToClear(9);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox011:
                hit = hitOrMiss(10);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox011);
                    int index = getIndexToClear(10);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox012:
                hit = hitOrMiss(11);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox012);
                    int index = getIndexToClear(11);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox013:
                hit = hitOrMiss(12);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox013);
                    int index = getIndexToClear(12);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox014:
                hit = hitOrMiss(13);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox014);
                    int index = getIndexToClear(13);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox015:
                hit = hitOrMiss(14);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox015);
                    int index = getIndexToClear(14);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
            case R.id.ibBox016:
                hit = hitOrMiss(15);
                if (hit){
                    totalActiveBoxes--;
                    Cats.hideCat(ibBox016);
                    int index = getIndexToClear(15);
                    activeBoxes.set(index, -1);
                    updateScore(1, "+");
                } else {
                    updateScore(1, "-");
                }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = (int)event.getX();
        touchY = (int)event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
        }
        return true;
    }

    public void updateTimer(long newStartTime, long newInterval){
        CountDown counter = new CountDown(newStartTime, newInterval);
        counter.start();
    }

    public ImageButton setViewToSend(int location, String showOrHide){
        switch (location) {
            case 0:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(0, 0);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(0, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox001;
            case 1:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(1, 1);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(1, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox002;
            case 2:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(2, 2);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(2, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox003;
            case 3:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(3, 3);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(3, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox004;
            case 4:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(4, 4);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(4, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox005;
            case 5:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(5, 5);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(5, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox006;
            case 6:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(6, 6);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(6, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox007;
            case 7:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(7, 7);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(7, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox008;
            case 8:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(8, 8);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(8, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox009;
            case 9:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(9, 9);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(9, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox010;
            case 10:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(10, 10);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(10, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox011;
            case 11:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(11, 11);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(11, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox012;
            case 12:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(12, 12);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(12, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox013;
            case 13:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(13, 13);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(13, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox014;
            case 14:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(14, 14);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(14, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox015;
            case 15:
                if (!activeBoxes.isEmpty()){
                    if (showOrHide.equals("Show")){
                        activeBoxes.set(15, 15);
                        totalActiveBoxes++;
                    } else {
                        activeBoxes.set(15, -1);
                        totalActiveBoxes--;
                    }
                }
                return ibBox016;
        }
        return ibBox001;
    }

    private void declareImages(){
        ibBox001 = (ImageButton) findViewById(R.id.ibBox001);
        ibBox001.setOnClickListener(this);
        ibBox002 = (ImageButton) findViewById(R.id.ibBox002);
        ibBox002.setOnClickListener(this);
        ibBox003 = (ImageButton) findViewById(R.id.ibBox003);
        ibBox003.setOnClickListener(this);
        ibBox004 = (ImageButton) findViewById(R.id.ibBox004);
        ibBox004.setOnClickListener(this);
        ibBox005 = (ImageButton) findViewById(R.id.ibBox005);
        ibBox005.setOnClickListener(this);
        ibBox006 = (ImageButton) findViewById(R.id.ibBox006);
        ibBox006.setOnClickListener(this);
        ibBox007 = (ImageButton) findViewById(R.id.ibBox007);
        ibBox007.setOnClickListener(this);
        ibBox008 = (ImageButton) findViewById(R.id.ibBox008);
        ibBox008.setOnClickListener(this);
        ibBox009 = (ImageButton) findViewById(R.id.ibBox009);
        ibBox009.setOnClickListener(this);
        ibBox010 = (ImageButton) findViewById(R.id.ibBox010);
        ibBox010.setOnClickListener(this);
        ibBox011 = (ImageButton) findViewById(R.id.ibBox011);
        ibBox011.setOnClickListener(this);
        ibBox012 = (ImageButton) findViewById(R.id.ibBox012);
        ibBox012.setOnClickListener(this);
        ibBox013 = (ImageButton) findViewById(R.id.ibBox013);
        ibBox013.setOnClickListener(this);
        ibBox014 = (ImageButton) findViewById(R.id.ibBox014);
        ibBox014.setOnClickListener(this);
        ibBox015 = (ImageButton) findViewById(R.id.ibBox015);
        ibBox015.setOnClickListener(this);
        ibBox016 = (ImageButton) findViewById(R.id.ibBox016);
        ibBox016.setOnClickListener(this);
    }

    public int getIndexToClear(int boxNr){
        return activeBoxes.indexOf(boxNr);
    }

    private void updateScore(int value, String sign){
        if(sign.equals("-")){
            score = score - value;
            tvScore.setText(String.valueOf(score));
            int progress = lifeBar.getProgress();
            ObjectAnimator animation = ObjectAnimator.ofInt(lifeBar, "progress", progress - 20);
            animation.setDuration(500);
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();
        } else {
            score = score + value;
            tvScore.setText(String.valueOf(score));
        }

    }

    public boolean hitOrMiss(int boxNr){
        int occurrences = Collections.frequency(activeBoxes, boxNr);
        return occurrences > 0;
    }

    private void hideBox(){
        String showOrHide = "Hide";
        int locationToHide = Cats.getLocationToHide(activeBoxes);
        ImageButton viewToHide = setViewToSend(locationToHide, showOrHide);
        Cats.hideCat(viewToHide);
    }

    private void showBox(){
        String showOrHide = "Show";
        int locationToShow = Cats.getLocationToShow(activeBoxes);
        ImageButton viewToShow = setViewToSend(locationToShow, showOrHide);
        Cats.showCat(viewToShow);
    }

    private void getOpenBoxNr(){
        if(totalActiveBoxes < 5){
            openPercent = Cats.genRand(100);
            if (openPercent > 85){
                openNr = 2;
            } else {
                openNr = 1;
            }
        } else {
            openNr = 1;
        }
    }

    public class CountDown extends CountDownTimer {

        public CountDown(long startTime, long interval) {
            super(startTime, interval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
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
        }
    }

    //PRINTING THE ARRAY LIST
    /*System.out.println("Array Content Pendulum C");
        for (int i = 0; i < activeBoxes.size(); i++) {
            System.out.println(activeBoxes.get(i));
        }*/

}
