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
