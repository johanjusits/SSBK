package com.example.johan.ssbk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

/**
 * Created by Johan on 2015-03-07.
 */
public class Activity_PlayScreen extends Activity implements View.OnClickListener {

    ImageButton ibBox001, ibBox002, ibBox003, ibBox004, ibBox005, ibBox006, ibBox007, ibBox008,
            ibBox009, ibBox010, ibBox011, ibBox012, ibBox013, ibBox014, ibBox015, ibBox016;
    ViewGroup layout_boxes;
    Toast toast;
    Context context;
    Activity activity;
    Handler handler001;
    Typeface font;
    Dialog dialog;
    String fontName = "coolvetica.ttf";
    TextView tvTimeLeftTitle, tvTimeLeft, tvScoreTitle, tvScore;
    Boolean updateTime = false;
    GridLayout boxGrid;

    private final long startTime = 59000;
    private final long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playscreen);

        font = Typeface.createFromAsset(getAssets(), fontName);

        tvTimeLeftTitle = (TextView) findViewById(R.id.tvTimeLeftTitle);
        tvTimeLeft = (TextView) findViewById(R.id.tvTimeLeft);
        tvScoreTitle = (TextView) findViewById(R.id.tvScoreTitle);
        tvScore = (TextView) findViewById(R.id.tvScore);

        tvTimeLeftTitle.setTypeface(font);
        tvTimeLeft.setTypeface(font);
        tvScoreTitle.setTypeface(font);
        tvScore.setTypeface(font);

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
            }
        }, 8500);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.boxGrid:
                int x = TouchCoords.getRelativeLeft(boxGrid);
                int y = TouchCoords.getRelativeTop(boxGrid);
                toast = Toast.makeText(context,"Hej", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP, x, y);
                toast.show();
                break;
        }
    }

    private void hideAll(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                hideAll((ViewGroup) child);
            } else {
                child.setVisibility(View.INVISIBLE);
                child.setClickable(false);
            }
        }
    }

    private void showAll(ViewGroup layout) {
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                showAll((ViewGroup) child);
            } else {
                child.setVisibility(View.VISIBLE);
                child.setClickable(true);
            }
        }
    }

    public void updateTimer(long newStartTime, long newInterval){
        CountDown counter = new CountDown(newStartTime, newInterval);
        counter.start();
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
        }
    }
}
