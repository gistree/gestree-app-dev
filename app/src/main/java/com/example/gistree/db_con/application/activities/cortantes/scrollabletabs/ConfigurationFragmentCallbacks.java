package com.example.gistree.db_con.application.activities.cortantes.scrollabletabs;

import android.content.Intent;

/**
 * Created by henrique on 1/11/17.
 */
public interface ConfigurationFragmentCallbacks {
    void onFrictionChanged(float friction);
    void openDialog(float friction);
    void openActivity(Intent intent);
}
