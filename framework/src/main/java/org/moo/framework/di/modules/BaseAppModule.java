package org.moo.framework.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.f2prateek.rx.preferences.RxSharedPreferences;

import org.moo.framework.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by moo on 15/9/18.
 */
@Module
public class BaseAppModule {
    private final Application application;

    public BaseAppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return this.application;
    }

    @Provides
    @Singleton
    @ApplicationContext
    public Context provideAppContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    public RxSharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        SharedPreferences preferences = context.getSharedPreferences("rx_sf", Context.MODE_PRIVATE);
        RxSharedPreferences rxPreferences = RxSharedPreferences.create(preferences);
        return rxPreferences;
    }
}
