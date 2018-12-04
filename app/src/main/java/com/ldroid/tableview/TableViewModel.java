package com.ldroid.tableview;

import com.ldroid.tableview.model.Cell;
import com.ldroid.tableview.model.ColumnHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ngliaxl on 2018/6/15.
 */
public abstract class TableViewModel<T> {

    protected List<T> mListData;
    protected List<ColumnHeader> mColumnHeaderList;
    private int mIndex = 0;

    public TableViewModel() {
        mListData = new ArrayList<>();
    }

    public abstract List<ColumnHeader> getColumnHeaderList();

    public abstract List<Cell> convert(T t);

    public List<List<Cell>> convert(List<T> source) {
        clearListData();
        List<List<Cell>> cellList = new ArrayList<>();
        if (source != null) {
            for (T t : source) {
                cellList.add(convert(t));
            }
        }
        return cellList;
    }


    public int getColumnWidth() {
        final List<ColumnHeader> headerList = getColumnHeaderList();
        if (headerList != null) {
            return headerList.get(mIndex++).getWidth();
        }
        return 0;
    }


    public void resetIndex() {
        mIndex = 0;
    }

    public int getIndex() {
        return mIndex;
    }


    public int getRowSize() {
        return mListData != null ? mListData.size() : 0;
    }

    public T getRowData(int position) {
        return mListData.get(position);
    }

    public List<T> getListData() {
        return mListData;
    }

    public void clearListData() {
        if (mListData != null) {
            mListData.clear();
        }
    }


}
