package com.example.gistree.db_con.application.activities.cortantes.scrollabletabs;
import android.app.Activity;
import android.os.Build;
import android.support.annotation.IdRes;
import android.view.View;
import android.view.ViewTreeObserver;
/**
 * Created by henrique on 1/11/17.
 */

public class ViewUtils {

    private ViewUtils() {}

    public static <V extends View> V findView(Activity activity, @IdRes int id) {
        //noinspection unchecked
        return (V) activity.findViewById(id);
    }

    public static <V extends View> V findView(View view, @IdRes int id) {
        //noinspection unchecked
        return (V) view.findViewById(id);
    }

    public static void removeOnGlobalLayoutListener(View view, ViewTreeObserver.OnGlobalLayoutListener listener) {
        final ViewTreeObserver observer = view.getViewTreeObserver();
        if (Build.VERSION.SDK_INT >= 16) {
            observer.removeOnGlobalLayoutListener(listener);
        } else {
            observer.removeGlobalOnLayoutListener(listener);
        }
    }
}
