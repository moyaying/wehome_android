package org.moo.framework.domain.action;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by moo on 16/1/26.
 */
public class ActionScheduler {
    static Scheduler mJobScheduler = Schedulers.from(new JobExecutor());
    static Scheduler mainThreadScheduler = AndroidSchedulers.mainThread();

    public static <T> Observable.Transformer<T, T> executeSchedulers() {
        return observable -> observable.subscribeOn(mJobScheduler)
                .observeOn(mainThreadScheduler);
    }

    public static <T> Observable.Transformer<T, T> subscribeOnJobScheduler() {
//        Schedulers.newThread();
//        Schedulers.computation();
        return observable -> observable.subscribeOn(mJobScheduler);
    }

    public static <T> Observable.Transformer<T, T> observeOnMainThread() {
        return observable -> observable.observeOn(mainThreadScheduler);
    }
}
