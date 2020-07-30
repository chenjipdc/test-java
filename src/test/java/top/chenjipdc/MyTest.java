package top.chenjipdc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/30 2:00 下午
 */
public class MyTest {

    @Before
    public void before(){
        System.out.println("before");
    }

    @After
    public void after(){
        System.out.println("after");
    }

    @Test
    public void test(){
        System.out.println("test");
    }

}
