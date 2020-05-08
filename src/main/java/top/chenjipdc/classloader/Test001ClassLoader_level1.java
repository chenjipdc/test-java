package top.chenjipdc.classloader;


/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-01 22:54
 */
public class Test001ClassLoader_level1 {

    public static void main(String[] args) {
        // null，c++实现的bootstrap classloader
        System.out.println(String.class.getClassLoader());

        // null，c++实现的bootstrap classloader
        System.out.println(sun.awt.HKSCS.class.getClassLoader());

        // sun.misc.Launcher$ExtClassLoader
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader());

        // sun.misc.Launcher$AppClassLoader
        System.out.println(Test001ClassLoader_level1.class.getClassLoader());

        // null，c++实现的bootstrap classloader
        System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader().getClass().getClassLoader());
        // null，c++实现的bootstrap classloader
        System.out.println(Test001ClassLoader_level1.class.getClassLoader().getClass().getClassLoader());
    }
}
