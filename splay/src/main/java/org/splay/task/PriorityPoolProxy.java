package org.splay.task;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jeffrey on 16-2-19.
 * 支持优先级的线程池管理类
 */
public class PriorityPoolProxy {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 128;
    private static final int KEEP_ALIVE = 1;
    private static final AtomicLong SEQ_SEED = new AtomicLong(0);

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "TID#" + mCount.getAndIncrement());
        }
    };

    private static final Comparator<Runnable> FIFO_CMP = new Comparator<Runnable>() {
        @Override
        public int compare(Runnable lhs, Runnable rhs) {
            if (lhs instanceof PriorityRunnable && rhs instanceof PriorityRunnable) {
                PriorityRunnable lpr = ((PriorityRunnable) lhs);
                PriorityRunnable rpr = ((PriorityRunnable) rhs);
                int result = lpr.priority.ordinal() - rpr.priority.ordinal();
                return result == 0 ? (int) (lpr.SEQ - rpr.SEQ) : result;
            } else {
                return 0;
            }
        }
    };

    private static final Comparator<Runnable> FILO_CMP = new Comparator<Runnable>() {
        @Override
        public int compare(Runnable lhs, Runnable rhs) {
            if (lhs instanceof PriorityRunnable && rhs instanceof PriorityRunnable) {
                PriorityRunnable lpr = ((PriorityRunnable) lhs);
                PriorityRunnable rpr = ((PriorityRunnable) rhs);
                int result = lpr.priority.ordinal() - rpr.priority.ordinal();
                return result == 0 ? (int) (rpr.SEQ - lpr.SEQ) : result;
            } else {
                return 0;
            }
        }
    };

    private ThreadPoolExecutor mThreadPoolExecutor;

    /**
     * 默认工作线程数5
     *
     * @param fifo 优先级相同时, 等待队列的是否优先执行先加入的任务.
     */
    public PriorityPoolProxy(boolean fifo) {
        this(CORE_POOL_SIZE, fifo);
    }

    /**
     * @param poolSize 工作线程数
     * @param fifo     优先级相同时, 等待队列的是否优先执行先加入的任务.
     */
    public PriorityPoolProxy(int poolSize, boolean fifo) {
        initThreadPoolExecutor(poolSize,fifo);
    }

    private ThreadPoolExecutor initThreadPoolExecutor(int poolSize, boolean fifo) {
        if (null == mThreadPoolExecutor) {
            synchronized (PriorityPoolProxy.class) {
                if (null == mThreadPoolExecutor) {
                    BlockingQueue<Runnable> mPoolWorkQueue =
                            new PriorityBlockingQueue<Runnable>(MAXIMUM_POOL_SIZE, fifo ? FIFO_CMP : FILO_CMP);
                    // 丢弃任务并抛出RejectedExecutionException异常。
                    RejectedExecutionHandler mRejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
                    mThreadPoolExecutor = new ThreadPoolExecutor(
                            poolSize,
                            MAXIMUM_POOL_SIZE,
                            KEEP_ALIVE,
                            TimeUnit.SECONDS,
                            mPoolWorkQueue,
                            sThreadFactory,
                            mRejectedExecutionHandler);
                }
            }
        }
        return mThreadPoolExecutor;
    }


    public int getPoolSize() {
        return mThreadPoolExecutor.getCorePoolSize();
    }

    public void setPoolSize(int poolSize) {
        if (poolSize > 0) {
            mThreadPoolExecutor.setCorePoolSize(poolSize);
        }

    }

    public ThreadPoolExecutor getThreadPoolExecutor() {
        return mThreadPoolExecutor;
    }

    public boolean isBusy() {
        return mThreadPoolExecutor.getActiveCount() >= mThreadPoolExecutor.getCorePoolSize();
    }

    /**
     * 执行一个任务
     *
     * @param runnable
     */
    public void execute(Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable) runnable).SEQ = SEQ_SEED.getAndIncrement();
        }
        mThreadPoolExecutor.execute(runnable);
    }

    /**
     * 提交任务
     */
    public Future<?> submitTask(Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable) runnable).SEQ = SEQ_SEED.getAndIncrement();
        }
        return mThreadPoolExecutor.submit(runnable);

    }

    /**
     * 移除任务
     */
    public void removeTask(Runnable runnable) {
        if (runnable instanceof PriorityRunnable) {
            ((PriorityRunnable) runnable).SEQ = SEQ_SEED.getAndDecrement();
        }
        mThreadPoolExecutor.remove(runnable);

    }
}
