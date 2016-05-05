package org.moo.framework.di.components;

/**
 * Created by arming on 2015/9/24.
 */

import android.app.Service;

import org.moo.framework.di.ForService;
import org.moo.framework.di.modules.BaseServiceModule;

import dagger.Component;

/**
 * 只提供ForActivity的
 * 只用来被依赖
 * Created by arming on 15/9/18.
 */
@ForService
@Component(modules = {
        BaseServiceModule.class
})
public interface BaseServiceComponent {
    Service service();
}
