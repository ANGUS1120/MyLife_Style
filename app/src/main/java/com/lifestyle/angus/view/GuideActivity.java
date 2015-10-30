package com.lifestyle.angus.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.lifestyle.angus.life_style.R;
import com.lifestyle.angus.util.BasePagerAdapter;
import com.lifestyle.angus.util.CreateShut;
import com.lifestyle.angus.util.SharedConfig;

public class GuideActivity extends Activity implements OnPageChangeListener, OnClickListener {
    private Context context;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private Button startButton;
    private LinearLayout indicatorLayout;
    private ArrayList<View> views;
    private ImageView[] indicators = null;
    private int[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        context = this;
        // 创建桌面快捷方式
        new CreateShut(this);
        // 设置引导图片
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  仅需在这设置图片 指示器和page自动添加
        images = new int[]{R.mipmap.guide01, R.mipmap.guide02,
                R.mipmap.guide03, R.mipmap.guide04};
        initView();
    }

    // 初始化视图
    private void initView() {
        // 实例化视图控件
        viewPager = (ViewPager) findViewById(R.id.viewpage);
        startButton = (Button) findViewById(R.id.start_Button);
        startButton.setOnClickListener(this);
        indicatorLayout = (LinearLayout) findViewById(R.id.indicator);
        views = new ArrayList<View>();
        indicators = new ImageView[images.length]; // 定义指示器数组大小
        for (int i = 0; i < images.length; i++) {
            // 循环加入图片
            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(images[i]);
            views.add(imageView);
            // 循环加入指示器
            indicators[i] = new ImageView(context);
            indicators[i].setBackgroundResource(R.mipmap.indicators_default);
            if (i == 0) {
                indicators[i].setBackgroundResource(R.mipmap.indicators_now);
            }
            indicatorLayout.addView(indicators[i]);
        }
        pagerAdapter = new BasePagerAdapter(views);
        viewPager.setAdapter(pagerAdapter); // 设置适配器
        viewPager.setOnPageChangeListener(this);
    }

    //按钮的点击事件
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_Button) {
            SharedPreferences shared = new SharedConfig(this).GetConfig();
            Editor editor = shared.edit();
            editor.putBoolean("First", false);
            editor.commit();

            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            this.finish();
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        // TODO Auto-generated method stub

    }

    // 监听viewpage
    @Override
    public void onPageSelected(int arg0) {
        // 显示最后一个图片时显示按钮
        if (arg0 == indicators.length - 1) {
            startButton.setVisibility(View.VISIBLE);
        } else {
            startButton.setVisibility(View.INVISIBLE);
        }
        // 更改指示器图片
        for (int i = 0; i < indicators.length; i++) {
            indicators[arg0].setBackgroundResource(R.mipmap.indicators_now);
            if (arg0 != i) {
                indicators[i]
                        .setBackgroundResource(R.mipmap.indicators_default);
            }
        }
    }
}
