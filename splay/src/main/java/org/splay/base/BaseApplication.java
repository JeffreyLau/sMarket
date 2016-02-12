package org.splay.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by jeffrey on 16-2-10.
 */
public class BaseApplication extends Application {


    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static Handler getMainHandler() {
        return mHandler;
    }

    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        mContext = getApplicationContext();
        mMainThread = Thread.currentThread();
        mMainLooper = getMainLooper();
        mMainThreadId = android.os.Process.myTid();
        mHandler = new Handler();
        super.onCreate();
    }
}
