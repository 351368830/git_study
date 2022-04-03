package com.zl.service;

import com.zl.util.ThreadPoolUtil;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PollingService {

    private volatile int flag = 0;

    public void polling() {
        ScheduledThreadPoolExecutor scheduler =
                new ScheduledThreadPoolExecutor(1);;
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(() -> {
            if (flag == 100) {
                System.out.println("flag: " + flag);
            }
            System.out.println(flag);
        }, 1, 5, TimeUnit.SECONDS);

    }

    private void setFlag() {
        for (int i = 0; i < 100; i++) {
            ++flag;
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        PollingService service = new PollingService();
        service.polling();
        service.setFlag();
    }

    public void poll() {
        ThreadPoolExecutor loader = ThreadPoolUtil.getLoader();
    }
}
