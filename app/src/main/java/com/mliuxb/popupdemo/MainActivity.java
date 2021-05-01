package com.mliuxb.popupdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.tv_select_user).setOnClickListener(this::onClick);
    }


    private PopupWindow mPopupWindow;
    
    public void onClick(View view) {
        final int viewId = view.getId();
        if (viewId == R.id.tv_select_user) {
            if (mPopupWindow == null) { //用户可能多次点击,不必重复创建对象
                mPopupWindow = new MyPopupWindow(MainActivity.this, (TextView) view);
            }
            mPopupWindow.showAsDropDown(view);//显示PopupWindow小弹窗
        }
    }
}