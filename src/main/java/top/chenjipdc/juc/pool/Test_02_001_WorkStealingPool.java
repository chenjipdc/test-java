package top.chenjipdc.juc.pool;

import top.chenjipdc.juc.interviews.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 *
 * 一般用来做并行计算
 */
public class Test_02_001_WorkStealingPool {

    public static void main(String[] args) {

        // 线程数动态增加
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < 20; i++) {
            final String str = "第" + i + "次打印";
            executorService.execute(() -> System.out.println(str + " => thread name: " + Thread.currentThread().getName()));
        }

        Test.sleep(10000);
        executorService.shutdown();
    }

}
