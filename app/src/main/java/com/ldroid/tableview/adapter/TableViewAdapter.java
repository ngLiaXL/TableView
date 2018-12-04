package com.ldroid.tableview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ldroid.tableview.R;
import com.ldroid.tableview.listener.OnScrollChangedListenerImpl;
import com.ldroid.tableview.model.Cell;
import com.ldroid.tableview.utils.DensityUtils;
import com.ldroid.tableview.utils.TextViewUtils;
import com.ldroid.tableview.view.MyHScrollView;

import java.util.ArrayList;
import java.util.List;

public class TableViewAdapter extends BaseAdapter {

    private MyHScrollView mScrollView;
    private List<List<Cell>> mListData;
    private int selectedIndex = -1;
    private Context mContext;

    public TableViewAdapter(Context context, MyHScrollView view) {
        this.mContext = context;
        this.mScrollView = view;
    }


    public void setListData(List<List<Cell>> listData) {
        this.mListData = listData;
        notifyDataSetChanged();
    }

    public void addListData(List<Cell> data) {
        if (mListData == null) {
            mListData = new ArrayList<>();
        }
        mListData.add(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return (mListData != null) ? mListData.size() : 0;
    }

    @Override
    public List<Cell> getItem(int position) {
        return (mListData != null && mListData.size() > position) ? mListData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_table_view_item,
                    null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        List<Cell> cellList = getItem(position);

        mScrollView.addScrollChangedListener(new OnScrollChangedListenerImpl(holder.scrollView));

        if (holder.layoutWrapper.getChildCount() == 0) {
            for (int cellIndex = 1; cellIndex < cellList.size(); cellIndex++) {
                TextView tv = TextViewUtils.generateText(mContext, cellList.get(cellIndex)
                        .getWidth());
                @android.support.annotation.IdRes int id = cellIndex;
                tv.setId(id);

                holder.layoutWrapper.addView(tv);
                if(cellIndex != cellList.size() - 1){
                    ImageView iv = TextViewUtils.generateSep(mContext);
                    holder.layoutWrapper.addView(iv);
                }
            }
        }

        for (int idRes = 1; idRes < cellList.size(); idRes++) {
            TextView tv = (TextView) holder.getView(idRes);
            tv.setText(cellList.get(idRes).getText());
        }

        // 设置第一列宽度和高度
        Cell headerCell = cellList.get(0);
        holder.rowHeader.setHeight(DensityUtils.dp2px(mContext, 45));

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.rowHeader
                .getLayoutParams();
        lp.width = DensityUtils.dp2px(mContext, headerCell.getWidth());
        holder.rowHeader.setLayoutParams(lp);
        holder.rowHeader.setText(headerCell.getText());


        holder.parentItem.setSelected(position == selectedIndex);

        return convertView;
    }


    static class ViewHolder {
        MyHScrollView scrollView;
        LinearLayout layoutWrapper;
        TextView rowHeader;
        View convertView;
        View parentItem;

        ViewHolder(View convertView) {
            this.convertView = convertView;
            scrollView = convertView.findViewById(R.id.myhscroll);
            layoutWrapper = convertView.findViewById(R.id.ll_item);
            rowHeader = convertView.findViewById(R.id.row_header);
            parentItem = convertView.findViewById(R.id.ll_parent_item);
        }

        View getView(int idRes) {
            return convertView.findViewById(idRes);
        }
    }

    public void setIndex(int position) {
        this.selectedIndex = position;
        notifyDataSetChanged();
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }


    public void remove(List<Cell> cells) {
        mListData.remove(cells);
        notifyDataSetChanged();
    }
}