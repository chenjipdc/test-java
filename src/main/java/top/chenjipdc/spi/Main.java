package top.chenjipdc.spi;

import java.util.ServiceLoader;

/**
 * @author chenjipdc@gmail.com
 * @date 2020-05-04 18:50
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Food> foods = ServiceLoader.load(Food.class);
        for (Food food : foods) {
            System.out.println(food.hashCode());
        }

        // 这里面的对象都是新的
        System.out.println("======================");
        ServiceLoader<Food> foods1 = ServiceLoader.load(Food.class);
        for (Food food : foods1) {
            System.out.println(food.hashCode());
        }

        System.out.println("======================");
        ServiceLoader<Food> foods2 = foods;
        System.out.println(foods.hashCode());
        System.out.println(foods2.hashCode());
    }
}
