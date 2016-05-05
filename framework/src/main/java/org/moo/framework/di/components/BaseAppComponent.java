package org.moo.framework.di.components;

import android.app.Application;
import android.content.Context;

import com.f2prateek.rx.preferences.RxSharedPreferences;

import org.moo.framework.di.ApplicationContext;
import org.moo.framework.di.modules.BaseAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 只提供singleton的
 * 只用来被依赖
 * Created by moo on 15/9/18.
 */
@Singleton
@Component(modules = {
        BaseAppModule.class,
})
public interface BaseAppComponent {
    //Exposed to sub-graphs.
    @ApplicationContext
    Context applicationContext();

    Application application();

    RxSharedPreferences rxSharedPreferences();
}
