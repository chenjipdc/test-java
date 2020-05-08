package top.chenjipdc.juc.future;

import java.util.concurrent.*;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 16:59
 */
public class Test001_Simple0 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        Future<Object> future = service.submit(() -> {
            System.out.println("已提交任务，开始处理");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务处理完成");
            return 100;
        });

        Object obj = future.get();
        System.out.println("得到的结果：" + obj);

        service.shutdown();
    }
}
