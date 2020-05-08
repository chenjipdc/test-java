package top.chenjipdc.juc.containers;

import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 11:24
 *
 *
 * 测试结果：
 * Hashtable => 5个线程 读1000000次 总共花费时间：1820 毫秒
 * Hashtable => 5个线程 写1000000次 总共花费时间：1139 毫秒
 *
 * Hashtable => 10个线程 读1000000次 总共花费时间：1956 毫秒
 * Hashtable => 10个线程 写1000000次 总共花费时间：2031 毫秒
 *
 * Hashtable => 50个线程 读1000000次 总共花费时间：9642 毫秒
 * Hashtable => 50个线程 写1000000次 总共花费时间：10837 毫秒
 *
 * Hashtable => 100个线程 读1000000次 总共花费时间：21035 毫秒
 * Hashtable => 100个线程 写1000000次 总共花费时间：21182 毫秒
 */

public class Test001_1_Hashtable {

    static final int write = 100_0000;
    static final int read = 100_0000;
    static final int thread = 100;
    static final String PRIFIX = "Hashtable => ";

    public static void main(String[] args) throws InterruptedException {

        read();

        write();
    }

    static void read() throws InterruptedException {
        CountDownLatch readLatch = new CountDownLatch(thread);
        Hashtable<String, Object> hashtable = new Hashtable<>(1000);
        for (int i = 0; i < read; i++) {
            hashtable.put("" + i, i);
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < thread; i++) {
            new Thread(() -> {
                for (int j = 0; j < read; j++) {
                    hashtable.get("" + j);
                }
                readLatch.countDown();
            }).start();
        }

        readLatch.await();
        System.out.println(PRIFIX + thread + "个线程 读" + read + "次 总共花费时间：" + (System.currentTimeMillis() - start) + " 毫秒");
    }

    static void write() throws InterruptedException {
        CountDownLatch writeLatch = new CountDownLatch(thread);
        Hashtable<String, Object> hashtable = new Hashtable<>(1000);
        long start = System.currentTimeMillis();

        for (int i = 0; i < thread; i++) {
            new Thread(() -> {
                for (int j = 0; j < write; j++) {
                    hashtable.put("" + j, j);
                }
                writeLatch.countDown();
            }).start();
        }

        writeLatch.await();
        System.out.println(PRIFIX + thread + "个线程 写" + write + "次 总共花费时间：" + (System.currentTimeMillis() - start) + " 毫秒");

    }
}
