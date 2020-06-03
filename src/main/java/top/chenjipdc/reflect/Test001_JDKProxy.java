package top.chenjipdc.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-15 15:09
 */
public class Test001_JDKProxy {

    public static void main(String[] args) {
        TestInterface testInterface = (TestInterface)Proxy.newProxyInstance(Test001_JDKProxy.class.getClassLoader(),
                new Class<?>[]{TestInterface.class}, new TestInvocationHandler(new DefaultTarget()));

        testInterface.print("我需要打印这个消息");
    }
}


interface TestInterface {
    String print(String msg);
}

interface Target {
    String print(String msg);
}

class DefaultTarget implements Target{

    public String print(String msg){
        String str = "这是代理添加的 => " + msg;
        System.out.println(str);
        return str;
    }
}


class TestInvocationHandler implements InvocationHandler {

    private Target target;

    public TestInvocationHandler(Target target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return target.print((String) args[0]);
    }
}

