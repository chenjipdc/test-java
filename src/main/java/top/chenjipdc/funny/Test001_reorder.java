package top.chenjipdc.funny;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-08 17:33
 *
 * 验证指令重排徐代码。
 *
 * 结果：
 * 第一次、
 * 循环了 0次
 * 总共循环了 63588次 重现出指令重排!
 *
 * 第二次、
 * 循环了 0次
 * 循环了 100000次
 * 循环了 200000次
 * 总共循环了 203764次 重现出指令重排!
 */
public class Test001_reorder {

    static int a,b,x,y;
    static long count = 0;

    public static void main(String[] args) throws InterruptedException {

        for (;;){
            x = 0; y = 0;
            a = 0; b = 0;

            Thread t1 = new Thread(() -> {
                shortWait(100000);
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                b = 1;
                y = a;
            });

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            if (x == 0 && y == 0){
                break;
            }else if (count % 100000 == 0){
                System.out.println("循环了 " + count + "次");
            }
            count++;
        }

        System.out.println("总共循环了 " + count + "次 重现出指令重排!");
    }

    public static void shortWait(long interval){
        long start = System.nanoTime();
        long end;
        do{
            end = System.nanoTime();
        }while(start + interval >= end);
    }
}
