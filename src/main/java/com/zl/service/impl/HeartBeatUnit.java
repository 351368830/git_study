package com.zl.service.impl;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.*;
@Service
public class HeartBeatUnit implements Runnable {

    private ScheduledExecutorService scheduler;

    private ExecutorService executor;

    private Callable<Boolean> task;

    public static volatile int flag = 1;

    public HeartBeatUnit() {
        scheduler = new ScheduledThreadPoolExecutor(1);
        executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        task = new HeartBeatThread();
    }

    @Override
    public void run() {
        System.out.println(this.hashCode());
        Future<Boolean> future = null;
        Boolean aBoolean = false;
        try {
            task.call();
//            future = executor.submit(task);
//            aBoolean = future.get();
            System.out.println("abc");
            if (flag == 4) {
                aBoolean = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if(future != null) {
//                future.cancel(true);
//            }
            if (!aBoolean) {
                scheduler.schedule(this, 1, TimeUnit.SECONDS);
                System.out.println(flag);
            } else {
//                if(future != null) {
//                    future.cancel(true);
//                }
                flag = 1;
//                scheduler.shutdown();
//                executor.shutdown();
                System.out.println("end");
            }
        }
    }

    public static void main(String[] args) {
        new HeartBeatUnit().run();
    }
}

class HeartBeatThread implements Callable<Boolean> {

    @Override
    public Boolean call() {
        try {
//            if (HeartBeatUnit.flag == 3) {
//                return Boolean.TRUE;
//            }
            HeartBeatUnit.flag++;
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + " - " + Thread.currentThread().getName() + ": take a beat.......");
        return Boolean.FALSE;
    }
}
