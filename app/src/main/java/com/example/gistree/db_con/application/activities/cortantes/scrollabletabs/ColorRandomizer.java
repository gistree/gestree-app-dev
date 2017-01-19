package com.example.gistree.db_con.application.activities.cortantes.scrollabletabs;

import android.os.SystemClock;

import java.util.Random;

/**
 * Created by henrique on 1/11/17.
 */

public class ColorRandomizer {

    private final Random mRandom;
    private final int[] mColors;
    private final int mMax;

    public ColorRandomizer(int[] colors) {
        this.mRandom = new Random(SystemClock.elapsedRealtime());
        this.mColors = colors;
        this.mMax = mColors.length - 1;
    }

    public int next() {
        final int index = mRandom.nextInt(mMax);
        return mColors[index];
    }
}
