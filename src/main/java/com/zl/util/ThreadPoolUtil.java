package com.zl.util;

import java.util.concurrent.*;

public class ThreadPoolUtil {

    private static final ThreadPoolExecutor loader = new ThreadPoolExecutor(
            8,
            4,
            60 * 1000,
            TimeUnit.MILLISECONDS,
            new SynchronousQueue<Runnable>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    private static final ScheduledThreadPoolExecutor scheduler =
            new ScheduledThreadPoolExecutor(1);

    public static ScheduledThreadPoolExecutor getScheduler() {
        return scheduler;
    }

    public static ThreadPoolExecutor getLoader() {
        return loader;
    }
}
