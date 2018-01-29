package com.wensihaihui.gaoshiqing.yunzhi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wensihaihui.gaoshiqing.common.app.mActivity;

public class MainActivity extends mActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initData() {

    }
}
