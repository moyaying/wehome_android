package org.moo.framework.di.components;

import android.app.Activity;

import org.moo.framework.di.ForActivity;
import org.moo.framework.di.modules.BaseActivityModule;

import dagger.Component;

/**
 * 只提供ForActivity的
 * 只用来被依赖
 * Created by moo on 15/9/18.
 */
@ForActivity
@Component(modules = {
        BaseActivityModule.class
})
public interface BaseActivityComponent {
    //Exposed to sub-graphs.
    Activity activity();
}
