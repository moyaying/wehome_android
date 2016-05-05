package org.moo.framework.common;

import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadHelper {
    private static Executor threadPoolExecutor = Executors.newCachedThreadPool();

    public static void runOnUiThread(Runnable run) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            run.run();
        } else {
            Handlers.sUIHandler.post(run);
        }
    }

    public static void runOnUiThread(Runnable run, long delayMillis) {
        Handlers.sUIHandler.postDelayed(run, delayMillis);
    }

    public static void runOnBackgroundThread(Runnable run) {
        if (Looper.myLooper() == Handlers.sBackgroundHandler.getLooper()) {
            run.run();
        } else {
            Handlers.sBackgroundHandler.post(run);
        }
    }

    public static void runOnBackgroundThread(Runnable run, long delayMillis) {
        Handlers.sBackgroundHandler.postDelayed(run, delayMillis);
    }

    public static void runOnAsyncThread(final Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public static void runOnAsyncThread(final Runnable runnable, long delayMillis) {
        Handlers.sUIHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                threadPoolExecutor.execute(runnable);
            }
        }, delayMillis);
    }

}
