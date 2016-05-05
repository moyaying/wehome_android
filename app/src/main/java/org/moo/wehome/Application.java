package org.moo.wehome;

import org.moo.wehome.common.Config;
import org.moo.wehome.di.DI;

/**
 * Created by moo on 16/4/16.
 */
public class Application extends android.app.Application{

    private static Application application;

    public static Application getInstance(){
        return application;
    }

    public Application(){
        application = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DI.initialize(this);
    }

}
