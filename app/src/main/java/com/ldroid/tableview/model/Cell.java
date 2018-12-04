package com.ldroid.tableview.model;

public class Cell {
    private String mId;
    private String mText;
    private int mWidth;

    public Cell(String mId, String mText) {
        this.mId = mId;
        this.mText = mText;
    }

    public Cell(String mId, String mText, int mWidth) {
        this.mId = mId;
        this.mText = mText;
        this.mWidth = mWidth;
    }

    public String getId() {
        return mId;
    }

    public String getText() {
        return mText;
    }

    public int getWidth() {
        return mWidth;
    }
}