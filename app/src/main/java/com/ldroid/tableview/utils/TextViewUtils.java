package com.ldroid.tableview.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ldroid.tableview.R;

/**
 * Created by ngliaxl on 2018/6/15.
 */
public class TextViewUtils {

    public static TextView generateText(Context ctx, int width) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dp2px(ctx,
                width), LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        TextView textView = (TextView) LayoutInflater.from(ctx).inflate(R.layout.layout_table_text_view, null);
        textView.setLayoutParams(params);
        return textView;
    }

    public static ImageView generateSep(Context ctx) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ImageView imageView = new ImageView(ctx);
        imageView.setLayoutParams(params);
        imageView.setBackgroundResource(R.color.colorTableSep);
        return imageView;
    }

}
