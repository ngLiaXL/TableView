package com.ldroid.tableview.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

@SuppressLint("ClickableViewAccessibility")
public  class ListViewAndHeadViewTouchLinstenerImpl implements View.OnTouchListener {

    private HorizontalScrollView mScrollView;

    public ListViewAndHeadViewTouchLinstenerImpl(HorizontalScrollView scrollView) {
        this.mScrollView = scrollView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mScrollView != null) {
            mScrollView.onTouchEvent(event);
        }
        return false;
    }
}