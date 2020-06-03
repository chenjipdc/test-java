package top.chenjipdc.reflect;

import top.chenjipdc.exception.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-15 15:44
 */
public class Test002_JDKProxy {

    public static void main(String[] args) {
        Test1 test1 = (Test1)Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{Test1Impl.class}, new AOPHandler());

        test1.print("打印消息");
        System.out.println(test1.add(2, 3));
    }


    interface Test1 {
        void print(String msg);

        int add(int a, int b);
    }

    static class Test1Impl implements Test1 {
        @Override
        public int add(int a, int b) {
            return a + b;
        }

        @Override
        public void print(String msg) {
            System.out.println(msg);
        }
    }

    static class AOPHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return 1;
        }
    }
}
