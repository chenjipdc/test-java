package top.chenjipdc.types;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenjipdc@gmail.com
 * @date 2020/7/31 1:41 下午
 */
public class Test001Type_generic {

}


@SuppressWarnings({"unchecked"})
class TestArraysGeneric<T> {

    Class<T> tClass;

    public TestArraysGeneric(Class<T> tClass) {
        this.tClass = tClass;
    }

    T[] arrays() {
        // 不能创建array类型的泛型数组，结果为：[null, null, null, null, null]
        return (T[]) Array.newInstance(tClass,
                5);
    }

    public static void main(String[] args) {
        // 结果为：[null, null, null, null, null]
        // 都为空
        System.out.println(Arrays.toString(new TestArraysGeneric<>(TestArraysGeneric.class).arrays()));
    }
}

class TestBoundGeneric {
    interface Animal {
        String name();
    }

    class Dog implements Animal {

        @Override
        public String name() {
            return "dog";
        }
    }

    class Cat implements Animal {

        @Override
        public String name() {
            return "cat";
        }
    }

    static void lookAnimals1(List<Animal> animals){

    }

    /**
     * 对于不确定或者不关心实际要操作的类型，可以使用无限制通配符
     *
     * @param animals
     */
    static void lookAnimals2(List<? extends Animal> animals){

    }

    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        // 这里会编译出错
//        lookAnimals1(dogs);

        // 这里是正常的
        lookAnimals2(dogs);
    }
}


class TestExceptionGeneric<T extends Exception> {

    // 可以抛出泛型异常
    void run() throws T {

    }
}