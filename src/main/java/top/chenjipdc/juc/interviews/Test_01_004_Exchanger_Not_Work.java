package top.chenjipdc.juc.interviews;

import java.util.concurrent.Exchanger;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 13:35
 *
 * 要求间隔输出：A1B2C3D4E5F6G7
 */
public class Test_01_004_Exchanger_Not_Work {

    private final String str1 = "ABCDEFG";
    private final String str2 = "1234567";


    private Exchanger<Character> exchanger = new Exchanger<>();

    private Thread t1;
    private Thread t2;

    public static void main(String[] args) throws InterruptedException {

        Test_01_004_Exchanger_Not_Work t = new Test_01_004_Exchanger_Not_Work();


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
                char c = str1.charAt(i);
                c = exchanger.exchange(c);
                System.out.print(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void print2(){
        for (int i = 0; i < str2.length(); i++) {
            try {
                char c = str2.charAt(i);
                c = exchanger.exchange(c);
                System.out.print(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
