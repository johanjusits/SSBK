package com.example.johan.ssbk;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
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

    public static Dialog yesNoWindow(String msg, final Context context, final Activity activity, final String buttonName, Typeface font){
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
        buttonDialogYes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (buttonName.equals("Exit")){
                    activity.finish();
                    activity.overridePendingTransition(0, 0);
                    dialog.dismiss();
                } else if (buttonName.equals("HighScore")){
                    toast = Toast.makeText(context,"Launch High Score Activity", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    dialog.dismiss();
                } else if (buttonName.equals("Play")){
                    toast = Toast.makeText(context,"Launch Play Activity", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    dialog.dismiss();
                }
            }
        });

        /* NO CLICKED */
        Button buttonDialogNo = (Button) dialog.findViewById(R.id.bConfirmCancel);
        buttonDialogNo.setTypeface(font);
        buttonDialogNo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

}
