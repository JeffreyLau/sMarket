package org.splay.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jeffrey on 16-2-16.
 */
public class ThreadPoolProxy {
    private ThreadPoolExecutor mExecutor;// 只需创建一次
    private int mMaximumPoolSize;
    private int mCorePoolSize;
    private long mKeepAliveTime;

    public ThreadPoolProxy(int corePoolSize,
                           int maximumPoolSize,
                           long keepAliveTime) {
        this.mCorePoolSize = corePoolSize;
        this.mMaximumPoolSize = maximumPoolSize;
        this.mKeepAliveTime = keepAliveTime;
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.execute(task);
    }

    private ThreadPoolExecutor initThreadPoolExecutor() {
        if (null == mExecutor) {
            synchronized (ThreadPoolProxy.class) {
                if (null == mExecutor) {
                    TimeUnit unit = TimeUnit.MILLISECONDS;
                    BlockingQueue<Runnable> workQueue =
                            new LinkedBlockingQueue<Runnable>();// 无界队列
                    ThreadFactory threadFactory = Executors.defaultThreadFactory();
                    // 丢弃任务并抛出RejectedExecutionException异常。
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
                    mExecutor = new ThreadPoolExecutor(mCorePoolSize,
                            mMaximumPoolSize, mKeepAliveTime, unit, workQueue, threadFactory, handler);
                }
            }
        }
        return mExecutor;
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task) {
        initThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     */
    public void removeTask(Runnable task) {
        initThreadPoolExecutor();
        mExecutor.remove(task);
    }


}
