package top.chenjipdc.juc.pool;

import top.chenjipdc.juc.interviews.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 *
 *
 * 单线程线程池，方便任务进入队列等待，而不需要自己手动实现任务的等待，方便使用。
 */
public class Test_01_001_SinglePoolExecutor {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final String str = "第" + i + "次打印";
            executorService.execute(() -> System.out.println(str + " => thread name: " + Thread.currentThread().getName()));
        }

        Test.sleep(10000);
        executorService.shutdown();
    }

}
