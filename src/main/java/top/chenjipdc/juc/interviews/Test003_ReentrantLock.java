package top.chenjipdc.juc.interviews;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 13:35
 *
 * 要求间隔输出：A1B2C3D4E5F6G7
 */
public class Test003_ReentrantLock {

    private final String str1 = "ABCDEFG";
    private final String str2 = "1234567";

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private Thread t1;
    private Thread t2;

    public static void main(String[] args) throws InterruptedException {

        Test003_ReentrantLock t = new Test003_ReentrantLock();


        t.t1 = new Thread(t::print1);
        t.t2 = new Thread(t::print2);
        t.t2.start();
        Test.sleep(10);
        t.t1.start();


        t.t1.join();
        t.t2.join();
    }

    private void print1(){
        for (int i = 0; i < str1.length(); i++) {
            lock.lock();
            try {
                char c = str1.charAt(i);
                System.out.print(c);
                try {
                    condition.signal();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
        }
    }

    private void print2(){
        for (int i = 0; i < str2.length(); i++) {

            try {
                lock.lock();
                condition.await();
                char c = str2.charAt(i);
                System.out.print(c);
                condition.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

}
