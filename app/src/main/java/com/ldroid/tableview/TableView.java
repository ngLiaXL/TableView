package com.ldroid.tableview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.AdapterView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ldroid.tableview.adapter.TableViewAdapter;
import com.ldroid.tableview.listener.ListViewAndHeadViewTouchLinstenerImpl;
import com.ldroid.tableview.model.ColumnHeader;
import com.ldroid.tableview.utils.DensityUtils;
import com.ldroid.tableview.utils.TextViewUtils;
import com.ldroid.tableview.view.MyHScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ngliaxl on 2018/6/14.
 */
public class TableView<T> extends LinearLayout {

    private ListView mListView;
    private TableViewAdapter mAdapter;

    private TextView mTvCorner;
    private LinearLayout mColumnHeader;
    private TableViewModel<T> mTableModel;

    private MyHScrollView mListScroll;

    public TableView(Context context) {
        super(context);
        initViews();
    }

    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TableView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
    }

    private void initViews() {
        Context context = getContext();
        LayoutInflater.from(context).inflate(R.layout.layout_table_view, this);

        mListScroll = findViewById(R.id.myhscroll);
        mTvCorner = findViewById(R.id.corner);
        mColumnHeader = findViewById(R.id.ll_item);

        mListView = findViewById(R.id.recycler_goods);
        mAdapter = new TableViewAdapter(context, mListScroll);
        mListView.setAdapter(mAdapter);
        mListView.setOnTouchListener(new ListViewAndHeadViewTouchLinstenerImpl(mListScroll));

    }


    private void initColumnHeaderView() {
        List<ColumnHeader> columnHeaderList = mTableModel.getColumnHeaderList();
        mColumnHeader.removeAllViews();
        if (columnHeaderList == null || columnHeaderList.isEmpty()) {
            return;
        }

        List<ColumnHeader> headerList = new ArrayList<>();
        for (ColumnHeader columnHeader : columnHeaderList) {
            headerList.add(columnHeader);
        }

        ColumnHeader corner = headerList.remove(0);
        mTvCorner.setText(corner.getText());

        LayoutParams lp = (LayoutParams) mTvCorner.getLayoutParams();
        lp.width = DensityUtils.dp2px(getContext(), corner.getWidth());
        mTvCorner.setLayoutParams(lp);


        int index = 0;
        for (ColumnHeader columnHeader : headerList) {
            TextView tv = TextViewUtils.generateText(getContext(), columnHeader.getWidth());
            tv.setBackgroundColor(getResources().getColor(R.color.table_header));
            tv.setText(columnHeader.getText());
            mColumnHeader.addView(tv);

            if (index++ != headerList.size() - 1) {
                ImageView iv = TextViewUtils.generateSep(getContext());
                mColumnHeader.addView(iv);
            }

        }

    }


    public void setCellList(List<T> cellList) {
        mAdapter.setListData(mTableModel.convert(cellList));
    }


    public void setCellListWithSelected(List<T> cellList) {
        mAdapter.setListData(mTableModel.convert(cellList));
        mAdapter.setIndex(getCount() - 1);
        mListScroll.fullScroll(HorizontalScrollView.FOCUS_LEFT);
        scrollMyListViewToBottom();
    }

    public void setCellListWithSelected(List<T> cellList, int index) {
        mAdapter.setListData(mTableModel.convert(cellList));
        if (index < getCount()) {
            mAdapter.setIndex(index);
        }
        mListScroll.fullScroll(HorizontalScrollView.FOCUS_LEFT);
    }


    private void scrollMyListViewToBottom() {
        mListView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                mListView.setSelection(mAdapter.getCount() - 1);
            }
        });
    }


    public void addCell(T cell) {
        mAdapter.addListData(mTableModel.convert(cell));
    }

    public void setTableViewModel(TableViewModel tableViewModel) {
        this.mTableModel = tableViewModel;
        initColumnHeaderView();
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        mListView.setOnItemClickListener(listener);
    }

    public void setSelectedIndex(int index) {
        mAdapter.setIndex(index);
    }

    public int getSelectedIndex() {
        return mAdapter.getSelectedIndex();
    }


    public T getSelectedItem() {
        int selectedIndex = mAdapter.getSelectedIndex();
        if (selectedIndex >= 0) {
            return mTableModel.getRowData(selectedIndex);
        }
        return null;
    }

    public int getCount() {
        return mAdapter.getCount();
    }

    public List<T> getListData() {
        return mTableModel.getListData();
    }

    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }
}
