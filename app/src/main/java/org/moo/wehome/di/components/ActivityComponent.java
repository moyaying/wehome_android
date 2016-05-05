package org.moo.wehome.di.components;

import org.moo.framework.di.ForActivity;
import org.moo.framework.di.components.BaseActivityComponent;
import org.moo.wehome.activity.MainActivity;
import org.moo.wehome.di.modules.ActivityModule;

import dagger.Component;

/**
 * Created by moo on 15/9/18.
 */
@ForActivity
@Component(
        dependencies = {
                AppComponent.class      //只能一级依赖
        },
        modules = {
                ActivityModule.class,
        }
)
public interface ActivityComponent extends BaseActivityComponent, AppComponent {
    //需要注入的类
    void inject(MainActivity activity);

    //提供依赖
}
