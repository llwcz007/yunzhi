package com.wensihaihui.gaoshiqing.yunzhi;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.wensihaihui.gaoshiqing.common.app.mActivity;
import com.wensihaihui.gaoshiqing.common.helper.NavHelper;
import com.wensihaihui.gaoshiqing.yunzhi.fragments.main.Fragment_Main_Class;
import com.wensihaihui.gaoshiqing.yunzhi.fragments.main.Fragment_Main_Home;
import com.wensihaihui.gaoshiqing.yunzhi.fragments.main.Fragment_Main_Live;
import com.wensihaihui.gaoshiqing.yunzhi.fragments.main.Fragment_Main_Me;
import com.wensihaihui.gaoshiqing.yunzhi.fragments.main.Fragment_Main_Talk;

public class MainActivity extends mActivity implements BottomNavigationView.OnNavigationItemSelectedListener,NavHelper.OnTabChangedListener<Integer>{

    private BottomNavigationView mBottomNavigationView;

    private FrameLayout mContainer;

    private NavHelper<Integer> mNavHelper;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        mContainer = (FrameLayout) findViewById(R.id.lay_container);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_main);
        mBottomNavigationView.setOnNavigationItemSelectedListener(MainActivity.this);

      //  BottomNavigationViewHelper.disableShiftMode(navigation);
        mNavHelper = new NavHelper<>(MainActivity.this,
                R.id.lay_container,
                getSupportFragmentManager(),
                MainActivity.this,
                null);

        mNavHelper.add(R.id.navigation_home,new NavHelper.Tab<Integer>(Fragment_Main_Home.class,R.string.navigation_home))
                .add(R.id.navigation_class,new NavHelper.Tab<Integer>(Fragment_Main_Class.class,R.string.navigation_class ))
                .add(R.id.navigation_talk,new NavHelper.Tab<Integer>(Fragment_Main_Talk.class,R.string.navigation_talk))
                .add(R.id.navigation_live,new NavHelper.Tab<Integer>(Fragment_Main_Live.class,R.string.navigation_live))
                .add(R.id.navigation_me,new NavHelper.Tab<Integer>(Fragment_Main_Me.class,R.string.navigation_me));

    }

    @Override
    protected void initData() {

        Menu menu = mBottomNavigationView.getMenu();

        menu.performIdentifierAction(R.id.navigation_home,0);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return mNavHelper.performClickMenu(item.getItemId());
    }

    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
       // Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show();
    }
}
