package top.chenjipdc.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-06 20:21
 */
public class Test {

    public static void main(String[] args) {
        int count = 100000;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < count; i++) {
            final int a = i;
            service.execute(() -> {
                TestWrapper wrapper = TestWrapper.currentWrapper();
                wrapper.setPayload(a + "");
                wrapper.toTest();
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 100000; i++) {
            service.execute(() -> {
                TestWrapper wrapper = TestWrapper.currentWrapper();
                if (wrapper != null && wrapper.getPayload() != null){
                    System.out.println(wrapper.getPayload());
                }
            });
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
    }
}

class TestWrapper {

    private String payload;

    private static ThreadLocal<TestWrapper> threadLocal = ThreadLocal.withInitial(TestWrapper::new);

    private void clear(){
        payload = null;
    }

    public static TestWrapper currentWrapper(){
        return threadLocal.get();
    }

    public String toTest(){
        threadLocal.remove();
        return null;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}