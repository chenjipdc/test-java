package top.chenjipdc.tclass;

import java.util.Random;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/31 10:33 上午
 */
public class Test001Class_init {

    public static void main(String[] args) throws ClassNotFoundException {

        // 直接.class不会初始化类对象
        Class class1 = TestClass1.class;
        System.out.println(TestClass1.A);
        System.out.println(TestClass1.B);

        System.out.println("----------");
        System.out.println(TestClass2.c);

        System.out.println("----------");
        // 调用Class.forName()方法会初始化类对象
        Class<?> testClass2 = Class.forName("top.chenjipdc.tclass.TestClass3");

    }
}

class TestClass1 {
    // 编译期常量，不需要类加载完
    static final int A = 10;

    // 运行期常量，需要类初始化
    static final int B = new Random().nextInt(1000);
    static {
        System.out.println("Initializing TestClass11111111");
    }
}

class TestClass2 {
    // 非常量，需要类加载完
    static int c = 444;
    static {
        System.out.println("Initializing TestClass22222222");
    }
}

class TestClass3 {
    static int c = 113212;
    static {
        System.out.println("Initializing TestClass333333333");
    }
}


