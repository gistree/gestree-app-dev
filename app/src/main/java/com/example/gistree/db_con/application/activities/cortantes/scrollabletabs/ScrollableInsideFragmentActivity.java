package com.example.gistree.db_con.application.activities.cortantes.scrollabletabs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.gistree.db_con.R;

/**
 * Created by henrique on 1/11/17.
 */
public class ScrollableInsideFragmentActivity extends BaseActivity {

    public static Intent makeIntent(Context context) {
        return new Intent(context, ScrollableInsideFragmentActivity.class);
    }

    @Override
    public void onCreate(Bundle sis) {
        super.onCreate(sis);
        setContentView(R.layout.activity_scrollable_inside_fragment);

        final Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_frame, ScrollableFragment.newInstance())
                    .commit();
        }
    }
}
