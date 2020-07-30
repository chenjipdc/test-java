package top.chenjipdc.exception;

import java.util.Collections;
import java.util.List;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-15 11:06
 */
public class Test {
    public static void main(String[] args) {
        List<Integer> list = Collections.singletonList(1);

        try {
            list.forEach(t -> {
                System.out.println(t);
                throw new RuntimeException("test-111");
            });
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("====");
        }


    }
}


class TestReturn {
    public static void main(String[] args) {
        // 10
        System.out.println(f());
    }

    public static int f(){
        try {
            return 1;
        }finally {
            return 10;
        }
    }
}

/**
 * try-with-resources    java7增加。用于自动释放资源，简化代码量
 * 结果：
 * 调用了run
 * 调用了close
 * finally
 */
class TestAutoClosed implements AutoCloseable{
    public static void main(String[] args) {
        try(
                TestAutoClosed autoClosed = new TestAutoClosed();
        ) {
            autoClosed.run();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
    }

    public void run(){
        System.out.println("调用了run");
    }

    @Override
    public void close() throws Exception {
        System.out.println("调用了close");
    }
}