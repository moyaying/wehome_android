package org.moo.wehome.di.modules;

import org.moo.framework.di.modules.BaseAppModule;
import org.moo.wehome.domain.manager.IAccountManager;
import org.moo.wehome.domain.manager.impl.AccountManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by moo on 15/9/17.
 * 应用级别模块，用于给全局依赖
 */
@Module(includes = BaseAppModule.class)
public class AppModule {

    public AppModule() {
    }

    //-------------------------------------------
    //
    //  Application层
    //
    //-------------------------------------------


    //-------------------------------------------
    //
    //  RxJava配置
    //
    //-------------------------------------------


    //-------------------------------------------
    //
    //  Data层
    //
    //-------------------------------------------
//    @Provides
//    @Singleton
//    IZoneRepository provideZoneRepository(ZoneRepository zoneRepository) {
//        return zoneRepository;
//    }


    //-------------------------------------------
    //
    //  Domain层
    //
    //-------------------------------------------
    @Provides
    @Singleton
    IAccountManager provideAccountManager() {
        return new AccountManager();
    }

}
