package org.splay.base;

import android.app.Application;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by jeffrey on 16-2-10.
 */
public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        super.onCreate();
    }
}
