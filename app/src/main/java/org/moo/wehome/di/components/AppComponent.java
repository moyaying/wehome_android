package org.moo.wehome.di.components;

import org.moo.framework.di.components.BaseAppComponent;
import org.moo.wehome.di.modules.AppModule;
import org.moo.wehome.domain.manager.IAccountManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by moo on 15/9/17.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
        }
)
public interface AppComponent extends BaseAppComponent {
    //inject class, 需要注入的类
    IAccountManager accountManager();

    //提供依赖
}
