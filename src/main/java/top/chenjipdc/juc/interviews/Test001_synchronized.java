package top.chenjipdc.juc.interviews;

import java.io.IOException;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 13:35
 *
 * 要求间隔输出：A1B2C3D4E5F6G7
 */
public class Test001_synchronized {

    private final String str1 = "ABCDEFG";
    private final String str2 = "1234567";

    private final Object lock = new Object();

    private Thread t1;
    private Thread t2;

    public static void main(String[] args) throws IOException, InterruptedException {

        Test001_synchronized t = new Test001_synchronized();


        t.t1 = new Thread(t::print2);
        t.t1.start();
        Test.sleep(100);
        t.t2 = new Thread(t::print1);
        t.t2.start();

        t.t1.join();
        t.t2.join();

    }

    private void print1(){
        for (int i = 0; i < str1.length(); i++) {
            synchronized (lock){
                char c = str1.charAt(i);
                System.out.print(c);
                try {
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void print2(){
        for (int i = 0; i < str2.length(); i++) {
            synchronized (lock){
                try {
                    lock.wait();
                    char c = str2.charAt(i);
                    System.out.print(c);
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
