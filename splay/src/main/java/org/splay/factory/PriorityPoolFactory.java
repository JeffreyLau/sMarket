package org.splay.factory;

import org.splay.task.PriorityPoolProxy;

/**
 * Created by jeffrey on 16-2-19.
 */
public class PriorityPoolFactory {

    private static PriorityPoolProxy mMainPool;
    private static PriorityPoolProxy mDownLoadPool;

    /**
     * 得到一个普通的线程池
     */
    public static PriorityPoolProxy getMainPool() {
        if (mMainPool == null) {
            synchronized (PriorityPoolFactory.class) {
                if (mMainPool == null) {
                    mMainPool = new PriorityPoolProxy(true);
                }
            }
        }
        return mMainPool;
    }

    /**
     * 得到一个下载的线程池
     */
    public static PriorityPoolProxy getDownLoadPool() {
        if (mDownLoadPool == null) {
            synchronized (PriorityPoolFactory.class) {
                if (mDownLoadPool == null) {
                    mDownLoadPool = new PriorityPoolProxy(true);
                }
            }
        }
        return mDownLoadPool;
    }
}
