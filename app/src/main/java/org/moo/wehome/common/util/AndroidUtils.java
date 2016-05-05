package org.moo.wehome.common.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import org.moo.wehome.common.Config;
import org.moo.wehome.common.UIHelper;

import java.io.File;
import java.util.UUID;

/**
 * Created by moo on 16/4/16.
 */
public class AndroidUtils {
    private static final String TAG = AndroidUtils.class.getName();
    private static final String CONFIG_UNIQUE_ID = "config_unique_id";

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 打开软键盘
     *
     * @param window
     */
    public static void showSoftKeyBoard(final Window window) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (window.getCurrentFocus() != null) {
                    InputMethodManager inputManager = (InputMethodManager) window.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.showSoftInputFromInputMethod(window.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }, 200);
    }

    /**
     * 关闭软键盘
     *
     * @param window
     */
    public static void hideSoftKeyBoard(final Window window) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (window.getCurrentFocus() != null) {
                    InputMethodManager inputManager = (InputMethodManager) window.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(window.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }, 200);
    }


    /**
     * dp to px
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dpToPx(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * sp to px
     *
     * @param context
     * @param sp
     * @return
     */
    public static int spToPx(Context context, int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    /**
     * 安装apk
     *
     * @param apkFilePath
     */
    public static void installApk(Context ctx, String apkFilePath) {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            UIHelper.toastMessage(ctx, "安装主程序失败，找不到主程序文件，请尝试重新更新。");
            return;
        }
        Log.d(TAG, "install apk: " + apkfile.getPath());
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.incubator.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "程序包名无法找到", e);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取当前程序版本名称
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // Get the package info
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "获取当前程序版本名称", e);
        }
        return versionName;
    }

    /**
     * 获取程序版本名称
     */
    public static String getAppVersionName(Context context, String packageName) {
        String versionName;
        try {
            // Get the package info
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            versionName = pi.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "获取程序版本名称", e);
            return "";
        }
        return versionName;
    }

    /**
     * 获取当前代码版本号
     */
    public static int getAppVersionCode(Context context) {
        int localVersion = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            localVersion = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "获取当前代码版本号", e);
        }
        return localVersion;
    }

    /**
     * 获取当前android手机型号
     */
    public static String getDriverModel() {
        String DeviceModel;
        DeviceModel = android.os.Build.MODEL;
        if (TextUtils.isEmpty(DeviceModel)) {
            return "";
        }
        return DeviceModel;
    }

    /**
     * 获取当前系统版本号
     */
    public static String getDriverVersionName() {
        String DeviceVersionName;
        DeviceVersionName = android.os.Build.VERSION.RELEASE;
        if (TextUtils.isEmpty(DeviceVersionName)) {
            return "";
        }
        return DeviceVersionName;
    }

    /**
     * 获取手机的IMEI码
     *
     * @return
     */
    public static String getIMEICode(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // check if has the permission
        if (PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {
            return tm.getDeviceId();
        } else {
            return null;
        }
    }

    /**
     * 获取设备唯一码（首先使用本地IMEI，如果获取失败使用保存的UUID）
     * UUID不带 “-”
     *
     * @param context
     * @return
     */
    public static String getUniqueID(Context context) {
        String uniqueID = getIMEICode(context);
        if (StringUtils.isEmpty(uniqueID)) {
            if (!Config.containsKey(CONFIG_UNIQUE_ID)) {
                Config.putString(CONFIG_UNIQUE_ID, UUID.randomUUID().toString().replaceAll("-", ""));
            }
            uniqueID = Config.getString(CONFIG_UNIQUE_ID);
        }
        return uniqueID;
    }
}
