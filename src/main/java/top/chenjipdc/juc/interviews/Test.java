package top.chenjipdc.juc.interviews;

import java.util.concurrent.TimeUnit;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 13:51
 */
public class Test {

    public static void main(String[] args) {
        new Thread(() -> {
            sleep(10000);
        }).start();
    }

    public static void sleep(int time){
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
