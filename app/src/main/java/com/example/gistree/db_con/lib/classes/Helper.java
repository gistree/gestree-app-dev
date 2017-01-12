package com.example.gistree.db_con.lib.classes;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.File;

public class Helper {

    public static void setupUI(final View view, final Activity main) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    Helper.hideSoftKeyboard(main);
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, main);
            }
        }
    }
    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public static void refreshDatabase(Context c) {
        File dbDelete = new File(Metadata.getDatabasePath(c) + Metadata.getDatabaseName(c));
        File joDelete = new File(Metadata.getDatabasePath(c) + Metadata.getDatabaseName(c) + "-journal");
        if (dbDelete.exists()) {
            if (dbDelete.delete()) {
                System.out.println("file Deleted ");
            } else {
                System.out.println("file not Deleted ");
            }
            if(joDelete.exists()){
                if(joDelete.delete()){
                    System.out.println("journal Deleted");
                }else{
                    System.out.println("journal not Deleted");
                }
            }
        }

    }
}
