package org.moo.wehome.activity;

import android.support.v7.app.AppCompatActivity;

import org.moo.framework.di.HasComponent;
import org.moo.wehome.di.DI;
import org.moo.wehome.di.components.ActivityComponent;
import org.moo.wehome.di.components.AppComponent;

/**
 * Created by moo on 16/4/16.
 */
public class BaseActivity extends AppCompatActivity implements HasComponent {
    private AppComponent appComponent;
    private ActivityComponent activityComponent;

    //依赖注入------------------
    public AppComponent appComponent() {
        return DI.appComponent();
    }

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DI.makeActivityComponent(this);
        }

        return activityComponent;
    }

    @Override
    public Object getComponent() {
        return getActivityComponent();
    }
}
