package com.example.gistree.db_con.application.controllers;

import android.view.View;
import android.widget.ScrollView;

import com.example.gistree.db_con.R;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

public class ScrollableController implements CanScrollVerticallyDelegate, OnScrollChangedListener {

    public ScrollView scroll;
    public View header;

    public ScrollableController(ScrollableLayout sl){
        this.scroll = (ScrollView) sl.findViewById(R.id.my_scroll_view);
        this.header = sl.findViewById(R.id.header);
    }
    @Override
    public boolean canScrollVertically(int direction) {
        return scroll.canScrollVertically(direction);
    }

    @Override
    public void onScrollChanged(int y, int oldY, int maxY) {
        header.setTranslationY(y / 2);
    }
}
