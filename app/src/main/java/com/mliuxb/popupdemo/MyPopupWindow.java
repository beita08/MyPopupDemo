package com.mliuxb.popupdemo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description: MyPopupWindow
 * Copyright  : Copyright (c) 2021
 * Company    : Jingdiao Software
 * Author     : 刘雄博
 * Date       : 2021/4/23 14:26
 */
public final class MyPopupWindow extends PopupWindow {
    private static final String TAG = "MyPopupWindow";
    private final TextView mTextView;//用于PopupWindow定位的TextView

    public MyPopupWindow(@NonNull Context context, @NonNull TextView textView) {
        super(context);
        this.mTextView = textView;
        setContentView(context);
        setWidth(textView.getWidth());
        setHeight(dip2px(context, 300));
        setFocusable(true);
        setBackgroundDrawable(new ColorDrawable());
    }


    public void setContentView(@NonNull Context context) {
        final RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        final DividerItemDecoration itemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(context.getResources().getDrawable(R.drawable.shape_recycler_list_divider_orange));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(new PopupAdapter());
        recyclerView.setBackgroundResource(R.drawable.shape_orange_rectangle_stroke_base);
        setContentView(recyclerView);
    }

    private class PopupAdapter extends RecyclerView.Adapter<PopupHolder> {

        @NonNull
        @Override
        public PopupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final Context context = parent.getContext();//context是当前Activity的对象
            final int dip10 = dip2px(context, 10);
            //final int dip15 = dip2px(context, 15);
            final TextView textView = new TextView(context);
            textView.setWidth(mTextView.getWidth());
            textView.setTextSize(18);
            //textView.setTextColor(context.getResources().getColor(R.color.blue_base));
            textView.setTextColor(context.getResources().getColorStateList(R.color.selector_text_blue_orange));
            textView.setPadding(dip10, dip10, dip10, dip10);
            //textView.setBackgroundResource(R.drawable.selector_recycler_item_gray_sky);
            return new PopupHolder(textView);
        }

        @Override
        public void onBindViewHolder(@NonNull final PopupHolder holder, int position) {
            final String[] selectItem = selectArr[position];
            holder.textView.setTag(R.id.UID, selectItem[0]);
            holder.textView.setTag(R.id.UTYPE, selectItem[1]);
            holder.textView.setText(selectItem[2]);

            holder.textView.setOnClickListener(v -> {
                mTextView.setTag(R.id.UID, holder.textView.getTag(R.id.UID));
                mTextView.setTag(R.id.UTYPE, holder.textView.getTag(R.id.UTYPE));
                mTextView.setText(holder.textView.getText());
                if (isShowing()) {
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return selectArr.length;
        }
    }

    private static class PopupHolder extends RecyclerView.ViewHolder {

        private final TextView textView;

        private PopupHolder(@NonNull TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    // 将dip或dp值转换为px值
    private int dip2px(Context context, double dipValue) {
        final double scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    //数组静态初始化：给出初始化值，由系统决定长度
    private final String[][] selectArr = {
            {"3", "LEADER", "张三"},
            {"4", "WORKER", "李四"},
            {"5", "WORKER", "王五"},
            {"6", "WORKER", "周六"},
            {"7", "WORKER", "赵七"},
            {"8", "WORKER", "刘八"}
    };
}
