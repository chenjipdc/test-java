package top.chenjipdc.juc.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 16:59
 */
public class Test002_Simple1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(() -> {
            Thread.sleep(3000);
            return 10;
        });

        new Thread(futureTask).start();

        Integer a = futureTask.get();

        System.out.println("future得到的结果：" + a);
    }
}
