package top.chenjipdc.juc.pool;

import top.chenjipdc.juc.interviews.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 *
 * 一般用来做定时调度任务
 */
public class Test_03_001_ScheduledPoolExecutor {

    public static void main(String[] args) {

        // 线程数动态增加
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 20; i++) {
            final String str = "第" + i + "次打印";
            executorService.execute(() -> System.out.println(str + " => thread name: " + Thread.currentThread().getName()));
        }
        Test.sleep(1000);

        final Runnable runnable = () -> {
            System.out.println("定时调用开始 => " + Thread.currentThread().getName());
            Test.sleep(3000);
            System.out.println("定时调用结束 => " + Thread.currentThread().getName());
        };

        executorService.schedule(runnable, 500, TimeUnit.MILLISECONDS);

        Test.sleep(10000);
        executorService.shutdown();
    }

}
