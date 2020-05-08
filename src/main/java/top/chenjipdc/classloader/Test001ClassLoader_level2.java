package top.chenjipdc.classloader;


import java.util.concurrent.Executors;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-01 22:54
 */
public class Test001ClassLoader_level2 {

    public static void main(String[] args) {

        // app classloader
        System.out.println(Test001ClassLoader_level2.class.getClassLoader());

        // null, bootstrap classloader
        System.out.println(Test001ClassLoader_level2.class.getClassLoader().getClass().getClassLoader());

        // ext classloader
        System.out.println(Test001ClassLoader_level2.class.getClassLoader().getParent());

        // null, bootstrap classloader
        System.out.println(Test001ClassLoader_level2.class.getClassLoader().getParent().getParent());

        Executors.newFixedThreadPool(1);
        Executors.newCachedThreadPool();
        Executors.newSingleThreadExecutor();
        Executors.newScheduledThreadPool(1);
        Executors.newWorkStealingPool();
    }
}
