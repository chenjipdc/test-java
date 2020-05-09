package top.chenjipdc.juc.pool;

import top.chenjipdc.juc.interviews.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 *
 * 一般用在线程大小固定的场景，需要自己评估线程数
 *
 * 一般线程数计算方法：
 * cpu：cpu核数，可以使用Runtime.getRuntime().availableProcessors()获取
 * cpu_usage：cpu使用率，0 <= cpu_usage <= 1
 * w/c：等待时间与计算时间比例
 * threads：线程数
 *
 * threads = cpu * cpu_usage * w/c
 *
 * 例如：
 * cpu：2核
 * cpu_usage使用率预期达到50%
 * 等待时间w为500毫秒，计算时间为100毫秒，则w/c = 500/100 = 5
 * 则线程数为 2 * 0.5 * 5 = 5
 *
 */
public class Test_01_002_FixedPoolExecutor {

    public static void main(String[] args) {

        // 可以指定线程数
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            final String str = "第" + i + "次打印";
            executorService.execute(() -> System.out.println(str + " => thread name: " + Thread.currentThread().getName()));
        }

        Test.sleep(10000);
        executorService.shutdown();
    }

}
