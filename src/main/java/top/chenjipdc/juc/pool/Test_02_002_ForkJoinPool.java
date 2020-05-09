package top.chenjipdc.juc.pool;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-09 09:38
 *
 * 使用ForkJoin计算的速度更加块。
 *
 * ForkJoinPool思想是分治，最后将结果汇总，有点类似MapReduce
 */
public class Test_02_002_ForkJoinPool {

    private static final int max = 1000_00000;
    private static long[] arr = new long[max];
    private static final int fork = 60000;
    private static final Random r = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < max; i++) {
            arr[i] = r.nextInt(1000);
        }
        long start = System.currentTimeMillis();
        System.out.println("sum: " + Arrays.stream(arr).sum());
        System.out.println("单线程 " + max + "次，共花费时间：" + (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new LongTask(0, max);
        pool.execute(task);
        System.out.println(task.join());
        System.out.println("ForkJoin " + max + "次，共花费时间：" + (System.currentTimeMillis() - start));

        pool.shutdown();
    }

    public static class LongTask extends RecursiveTask<Long> {

        private int start;
        private int end;

        public LongTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start >= fork){
                int middle = (end - start)/2 + start;
                ForkJoinTask<Long> task1 = new LongTask(start, middle);
                ForkJoinTask<Long> task2 = new LongTask(middle, end);

                task1.fork();
                task2.fork();

                return task1.join() + task2.join();
            }else {
                long sum = 0;
                for (int i = start; i < end; i++) {
                    sum += arr[i];
                }
                return sum;
            }
        }
    }
}
