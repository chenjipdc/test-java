package top.chenjipdc.juc.containers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 11:24
 *
 *
 * 测试结果：
 * ConcurrentHashMap => 5个线程 读1000000次 总共花费时间：230 毫秒
 * ConcurrentHashMap => 5个线程 写1000000次 总共花费时间：416 毫秒
 *
 * ConcurrentHashMap => 10个线程 读1000000次 总共花费时间：384 毫秒
 * ConcurrentHashMap => 10个线程 写1000000次 总共花费时间：652 毫秒
 *
 * ConcurrentHashMap => 50个线程 读1000000次 总共花费时间：1335 毫秒
 * ConcurrentHashMap => 50个线程 写1000000次 总共花费时间：2014 毫秒
 *
 * ConcurrentHashMap => 100个线程 读1000000次 总共花费时间：2891 毫秒
 * ConcurrentHashMap => 100个线程 写1000000次 总共花费时间：3914 毫秒
 */

public class Test003_1_ConcurrentHashMap {

    static final int write = 100_0000;
    static final int read = 100_0000;
    static final int thread = 100;
    static final String PRIFIX = "ConcurrentHashMap => ";

    public static void main(String[] args) throws InterruptedException {

        read();

        write();
    }

    static void read() throws InterruptedException {
        CountDownLatch readLatch = new CountDownLatch(thread);
        Map<String, Object> hashMap = new ConcurrentHashMap<>(1000);
        for (int i = 0; i < read; i++) {
            hashMap.put("" + i, i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < thread; i++) {
            new Thread(() -> {
                for (int j = 0; j < read; j++) {
                    hashMap.get("" + j);
                }
                readLatch.countDown();
            }).start();
        }

        readLatch.await();
        System.out.println(PRIFIX + thread + "个线程 读" + read + "次 总共花费时间：" + (System.currentTimeMillis() - start) + " 毫秒");
    }

    static void write() throws InterruptedException {
        CountDownLatch writeLatch = new CountDownLatch(thread);
        Map<String, Object> hashMap = new ConcurrentHashMap<>(1000);

        long start = System.currentTimeMillis();
        for (int i = 0; i < thread; i++) {
            new Thread(() -> {
                for (int j = 0; j < write; j++) {
                    hashMap.put("" + j, j);
                }
                writeLatch.countDown();
            }).start();
        }

        writeLatch.await();
        System.out.println(PRIFIX + thread + "个线程 写" + write + "次 总共花费时间：" + (System.currentTimeMillis() - start) + " 毫秒");

    }
}
