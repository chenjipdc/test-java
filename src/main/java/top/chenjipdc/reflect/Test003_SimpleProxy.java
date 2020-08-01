package top.chenjipdc.reflect;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/31 11:21 上午
 */
public class Test003_SimpleProxy {
    public static void main(String[] args) {
        SimpleInterface simpleInterface = new SimpleReal();
        simpleInterface.doSomething();

        System.out.println("--------");
        SimpleProxy simpleProxy = new SimpleProxy(simpleInterface);
        simpleProxy.doSomething();
    }
}

interface SimpleInterface {
    void doSomething();
}

class SimpleReal implements SimpleInterface {

    @Override
    public void doSomething() {
        System.out.println("SimpleReal doSomething");
    }
}

class SimpleProxy implements SimpleInterface {

    private SimpleInterface proxied;

    public SimpleProxy(SimpleInterface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxied.doSomething();
    }
}
