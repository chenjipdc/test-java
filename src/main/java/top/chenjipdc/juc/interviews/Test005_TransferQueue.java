package top.chenjipdc.juc.interviews;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 13:35
 *
 * 要求间隔输出：A1B2C3D4E5F6G7
 */
public class Test005_TransferQueue {

    private final String str1 = "ABCDEFG";
    private final String str2 = "1234567";


    private TransferQueue<Character> queue = new LinkedTransferQueue<>();

    private Thread t1;
    private Thread t2;

    public static void main(String[] args) throws InterruptedException {

        Test005_TransferQueue t = new Test005_TransferQueue();


        t.t1 = new Thread(t::print1);
        t.t2 = new Thread(t::print2);
        t.t1.start();
        Test.sleep(10);
        t.t2.start();


        t.t1.join();
        t.t2.join();
    }

    private void print1(){
        for (int i = 0; i < str1.length(); i++) {
            try {
                queue.transfer(str1.charAt(i));
                System.out.print(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void print2(){
        for (int i = 0; i < str2.length(); i++) {
            try {
                System.out.print(queue.take());
                queue.transfer(str2.charAt(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
