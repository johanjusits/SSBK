package com.example.johan.ssbk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import static android.graphics.Color.TRANSPARENT;


public class Activity_StartScreen extends Activity implements View.OnClickListener {

    TextView title;
    Button bExit, bHighScore, bPlay;
    Context context;
    Activity activity;
    Dialog dialog;
    String dialogMessage, buttonName;
    Typeface font;
    String fontName = "coolvetica.ttf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        context = Activity_StartScreen.this;
        activity = Activity_StartScreen.this;

        title = (TextView) findViewById(R.id.tvTitle);
        bExit = (Button) findViewById(R.id.bExit);
        bHighScore = (Button) findViewById(R.id.bHighScore);
        bPlay = (Button) findViewById(R.id.bPlay);

        bExit.setOnClickListener(this);
        bHighScore.setOnClickListener(this);
        bPlay.setOnClickListener(this);

        font = Typeface.createFromAsset(getAssets(), fontName);

        title.setTypeface(font);
        bExit.setTypeface(font);
        bHighScore.setTypeface(font);
        bPlay.setTypeface(font);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bExit:
                dialogMessage = "Do you want to quit?";
                buttonName = "Exit";
                dialog = DialogWindows.yesNoWindow(dialogMessage, context, activity, buttonName, font);
                dialog.show();
                break;
            case R.id.bHighScore:
                dialogMessage = "Look at high score?";
                buttonName = "HighScore";
                dialog = DialogWindows.yesNoWindow(dialogMessage, context, activity, buttonName, font);
                dialog.show();
                break;
            case R.id.bPlay:
                dialogMessage = "Start a game?";
                buttonName = "Play";
                dialog = DialogWindows.yesNoWindow(dialogMessage, context, activity, buttonName, font);
                dialog.show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
