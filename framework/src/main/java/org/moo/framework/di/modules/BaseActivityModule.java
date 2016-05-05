package org.moo.framework.di.modules;

import android.app.Activity;

import org.moo.framework.di.ForActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by moo on 15/9/18.
 */
@Module
public class BaseActivityModule {
    private final Activity activity;

    public BaseActivityModule(Activity activity) {
        this.activity = activity;
    }

    @ForActivity
    @Provides
    Activity provideActivity() {
        return activity;
    }
}
