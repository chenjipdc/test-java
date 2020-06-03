package top.chenjipdc;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-01 23:58
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("===== classpath =====");
        System.out.println(System.getProperty("java.class.path").replaceAll(":", System.lineSeparator()));

        System.out.println("====== ext =====");
        System.out.println(System.getProperty("java.ext.dirs").replaceAll(":", System.lineSeparator()));

        System.out.println("====== app =====");
        System.out.println(System.getProperty("sun.boot.class.path").replaceAll(":", System.lineSeparator()));


        System.out.println("========= system keys ========");
        for (Object o : System.getProperties()
                .keySet()) {
            System.out.println(o + " \t\t=> " + System.getProperty((String) o));
        }


        System.out.println(1024 * 1024);

        System.out.println(10_10);
    }
}
