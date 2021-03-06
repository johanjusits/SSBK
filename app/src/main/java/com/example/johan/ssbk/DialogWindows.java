package com.example.johan.ssbk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.graphics.Color.TRANSPARENT;

/**
 * Created by Johan on 2015-03-01.
 */
public class DialogWindows {

    static Toast toast;
    static Intent intentStartGame;

    public static Dialog yesNoWindow(String msg, final Context context, final Activity activity, final String buttonName, Typeface font,
                                     String yesButtonText, String noButtonText){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_yesorno);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.setCancelable(false);

        TextView tvText = (TextView) dialog.findViewById(R.id.tvMsg);
        tvText.setText(msg);
        tvText.setTypeface(font);

        /* YES CLICKED */
        Button buttonDialogYes = (Button) dialog.findViewById(R.id.bConfirmOk);
        buttonDialogYes.setTypeface(font);
        buttonDialogYes.setText(yesButtonText);
        buttonDialogYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (buttonName){
                    case "Exit":
                        activity.finish();
                        activity.overridePendingTransition(0, 0);
                        dialog.dismiss();
                            break;
                    case "HighScore":
                        toast = Toast.makeText(context,"Launch High Score Activity", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        dialog.dismiss();
                            break;
                    case "Play":
                        intentStartGame = new Intent(context, Activity_PlayScreen.class);
                        intentStartGame.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        activity.startActivity(intentStartGame);
                        dialog.dismiss();
                            break;
                }
            }
        });

        /* NO CLICKED */
        Button buttonDialogNo = (Button) dialog.findViewById(R.id.bConfirmCancel);
        buttonDialogNo.setTypeface(font);
        buttonDialogNo.setText(noButtonText);
        buttonDialogNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(buttonName.equals("Restart")){
                    dialog.dismiss();
                    activity.finish();
                } else {
                    dialog.dismiss();
                }
            }
        });

        return dialog;
    }

    public static Dialog gameOverWindow(String msg, final Context context, final Activity activity, Typeface font,
                                        String yesButtonText, String noButtonText, String gameOverMsg, int finalScore){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_youdied);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(TRANSPARENT));
        dialog.setCancelable(false);

        TextView tvText = (TextView) dialog.findViewById(R.id.tvMsg);
        TextView tvDeathMsg = (TextView) dialog.findViewById(R.id.tvRdmDeathMsg);
        TextView tvFinalScore = (TextView) dialog.findViewById(R.id.tvFinalScore);
        tvText.setText(msg);
        tvDeathMsg.setText(gameOverMsg);
        tvFinalScore.setText(String.valueOf(finalScore) + "p");
        tvText.setTypeface(font);
        tvDeathMsg.setTypeface(font);
        tvFinalScore.setTypeface(font);

        /* YES CLICKED */
        Button buttonDialogYes = (Button) dialog.findViewById(R.id.bConfirmOk);
        buttonDialogYes.setTypeface(font);
        buttonDialogYes.setText(yesButtonText);
        buttonDialogYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = activity.getIntent();
                activity.finish();
                activity.startActivity(intent);
                dialog.dismiss();
            }
        });

        /* NO CLICKED */
        Button buttonDialogNo = (Button) dialog.findViewById(R.id.bConfirmCancel);
        buttonDialogNo.setTypeface(font);
        buttonDialogNo.setText(noButtonText);
        buttonDialogNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
                activity.finish();
            }
        });

        return dialog;
    }

}
