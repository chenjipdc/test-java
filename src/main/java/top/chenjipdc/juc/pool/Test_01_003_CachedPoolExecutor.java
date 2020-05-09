package top.chenjipdc.juc.pool;

import top.chenjipdc.juc.interviews.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 *
 *
 * 一般在线程数不定的的场景下使用，任务数可能忽然很高，也可能忽然很低。
 */
public class Test_01_003_CachedPoolExecutor {

    public static void main(String[] args) {

        // 线程数动态增加
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            final String str = "第" + i + "次打印";
            executorService.execute(() -> System.out.println(str + " => thread name: " + Thread.currentThread().getName()));
        }

        Test.sleep(10000);
        executorService.shutdown();
    }

}
