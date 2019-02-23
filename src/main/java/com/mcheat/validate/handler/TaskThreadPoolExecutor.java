package com.mcheat.validate.handler;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池类
 *
 * @Author McHeat
 * @Date 2019/2/1 10:42
 * @Version 1.0.0
 */
public class TaskThreadPoolExecutor {

    private static ThreadPoolExecutor executor;

    /**
     * 初始化线程池
     *
     * @return 线程池
     */
    public static ThreadPoolExecutor getExecutor() {
        if (executor == null) {
            synchronized (TaskThreadPoolExecutor.class) {
                if (executor == null) {
                    executor = new ThreadPoolExecutor(10, 50,
                            1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        return executor;
    }

}
