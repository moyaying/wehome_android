package org.moo.wehome.di;

import android.app.Activity;
import android.app.Application;

import org.moo.framework.di.modules.BaseActivityModule;
import org.moo.framework.di.modules.BaseAppModule;
import org.moo.wehome.di.components.ActivityComponent;
import org.moo.wehome.di.components.AppComponent;
import org.moo.wehome.di.components.DaggerActivityComponent;
import org.moo.wehome.di.components.DaggerAppComponent;

/**
 * Created by moo on 15/10/23.
 * <p>
 * 依赖注入
 */
public class DI {
    private static AppComponent appComponent;

    public static void initialize(Application application) {
        initComponent(application);
    }

    /**
     * 初始化依赖关系
     */
    private static void initComponent(Application application) {
//        String userAgent = String.format("aipai/Android/aipai/aipai/v(%d)", SystemUtil.getVersionCode(application));
        appComponent = DaggerAppComponent.builder()
                .baseAppModule(new BaseAppModule(application))
//                .basePackageContextModule(new BasePackageContextModule(application))
//                .networkModule(new NetworkModule(userAgent))
                .build();
    }

    public static AppComponent appComponent() {
        return appComponent;
    }

    public static ActivityComponent makeActivityComponent(Activity activity) {
        return DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .baseActivityModule(new BaseActivityModule(activity))
                .build();
    }

}
