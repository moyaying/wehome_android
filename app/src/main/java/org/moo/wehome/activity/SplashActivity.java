package org.moo.wehome.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.lzyzsd.randomcolor.RandomColor;

import org.moo.framework.common.ThreadHelper;
import org.moo.wehome.R;
import org.moo.wehome.common.Config;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by moo on 16/4/16.
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Config.init(this.getApplicationContext());

        boolean isFirstTimeRun = Config.isFirstTimeRun();
        BGABanner banner = (BGABanner) findViewById(R.id.banner_splash_pager);
        View launcherView = findViewById(R.id.launcher_view);

        if (isFirstTimeRun) {
            launcherView.setVisibility(View.GONE);
            banner.setVisibility(View.VISIBLE);

            banner.setVisibility(View.VISIBLE);
            // 用Java代码方式设置切换动画
            banner.setTransitionEffect(BGABanner.TransitionEffect.Cube);
            // banner.setPageTransformer(new RotatePageTransformer());
            // 设置page切换时长
            banner.setPageChangeDuration(1000);
            List<View> views = new ArrayList<>();
//            views.add(getPageView(R.mipmap.guide_1));
//            views.add(getPageView(R.mipmap.guide_2));
//            views.add(getPageView(R.mipmap.guide_3));
            views.add(getPageView("第一页"));
            views.add(getPageView("第二页"));
            views.add(getPageView("第三页"));

            View lastView = getLayoutInflater().inflate(R.layout.view_splash_last, null);
            views.add(lastView);
            lastView.findViewById(R.id.btn_last_main).setOnClickListener(v -> {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            });
            banner.setViews(views);
            // banner.setCurrentItem(1);
        } else {
            banner.setVisibility(View.GONE);
            launcherView.setVisibility(View.VISIBLE);

            ThreadHelper.runOnUiThread(() -> {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }, 2000);
        }
    }

    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    private View getPageView(String str){
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        RandomColor randomColor = new RandomColor();
        int color = randomColor.randomColor();
        textView.setBackgroundColor(color);
        textView.setText(str);
        return textView;
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
