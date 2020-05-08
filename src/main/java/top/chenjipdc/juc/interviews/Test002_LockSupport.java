package top.chenjipdc.juc.interviews;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 13:35
 *
 * 要求间隔输出：A1B2C3D4E5F6G7
 */
public class Test002_LockSupport {

    private final String str1 = "ABCDEFG";
    private final String str2 = "1234567";

    private Thread t1;
    private Thread t2;

    public static void main(String[] args) throws InterruptedException {

        Test002_LockSupport t = new Test002_LockSupport();


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
            char c = str1.charAt(i);
            System.out.print(c);
            LockSupport.unpark(t2);
            LockSupport.park();
        }
    }

    private void print2(){
        for (int i = 0; i < str2.length(); i++) {
            LockSupport.park();
            char c = str2.charAt(i);
            System.out.print(c);
            LockSupport.unpark(t1);
        }
    }

}
