package top.chenjipdc.juc.pool;

import top.chenjipdc.juc.interviews.Test;

import java.util.concurrent.*;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 * 
 * 7个参数
 * 1、核心线程数。
 * 2、最大线程数。
 * 3、线程空闲时间。
 * 4、线程空闲时间。
 * 5、任务队列，存Runnable。
 * 6、线程工厂，线程是怎么生产的。
 * 7、拒绝策略，当队列满了的时候，采取什么策略。
 *
 * 队列满了之后的拒绝策略默认有4种：
 * AbortPolicy => 抛出异常
 * DiscardPolicy => 丢弃任务
 * DiscardOldestPolicy => 丢弃最老的任务。需要实时信息的应用，如：游戏最新坐标位置，无人控制最新指令
 * CallerRunsPolicy => 谁提交的谁运行
 *
 * 一般拒绝策略都是自己实现的，主要是为了能够在处理大流量时能够暂时将数据放到消息队列里面去存取，等线程能够处理了再拿出来处理，确保数据不会丢失。
 */
public class Test_01_004_ThreadPoolExecutor {

    public static void main(String[] args) {
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 4; i++) {
            executor.execute(() -> {
                Test.sleep(3000);
                System.out.println(Thread.currentThread().getName() + " 线程执行了 " + System.currentTimeMillis()/1000);
            });
        }
        System.out.println("主线程名称：" + Thread.currentThread().getName());

        Test.sleep(1000);
        executor.execute(() -> {
            System.out.println("队列满了，谁提交的就自己执行吧 => " + Thread.currentThread().getName());
        });

        Test.sleep(10000);
        executor.shutdown();
    }

}
