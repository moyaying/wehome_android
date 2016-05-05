package org.moo.framework.common;

import android.os.Handler;
import android.os.Looper;

public class Handlers {
	static final Handler sUIHandler = new Handler(Looper.getMainLooper()); 
	static final Handler sBackgroundHandler = BackgroundHandlerThread.getInstance().getHandler();
}
