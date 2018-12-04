package com.ldroid.tableview.listener;


import com.ldroid.tableview.view.MyHScrollView;

public class OnScrollChangedListenerImpl implements MyHScrollView.OnScrollChangedListener {

    private MyHScrollView mScrollView;

    public OnScrollChangedListenerImpl(MyHScrollView scrollView) {
        mScrollView = scrollView;
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (mScrollView != null) {
            mScrollView.smoothScrollTo(l, t);
        }
    }
}