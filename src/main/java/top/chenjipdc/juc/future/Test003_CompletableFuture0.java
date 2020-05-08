package top.chenjipdc.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 16:59
 */
public class Test003_CompletableFuture0 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println("开始处理");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("处理完成");
        });

        System.out.println("这里是主线程输出");

        Thread.sleep(1000);

        System.out.println("这里是主线程等待后输出");

        Object o = future.get();
        System.out.println("得到的处理结果：" + o);
    }
}
