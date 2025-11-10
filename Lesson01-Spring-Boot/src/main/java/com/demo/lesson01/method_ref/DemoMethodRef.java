package com.demo.lesson01.method_ref;

import java.util.Arrays;

@FunctionalInterface
interface ExecuteFunction{
    public int execute(int a, int b);
}
class MathUtils {
    public MathUtils() {}
    public MathUtils(String str) {
        System.out.println("MathUtils: " + str);
    }
    public static int sum(int a, int b) {
        return a+b;
    }
    public static int minus(int a, int b) {
        return a - b;
    }
    public int multiply(int a, int b) {
        return a * b;
    }
}



public class DemoMethodRef {
    public static int doAction(int a, int b, ExecuteFunction func) {
        return func.execute(a, b);
    }
    public static void main(String[] args) {
        int a = 10;
        int b = 20;
        int sum = doAction(a, b, MathUtils::sum);
        System.out.println(a + " + " + b  + " = " + sum);
        int minus = doAction(a, b, MathUtils::minus);
        System.out.println(a + " - " + b + " = " + minus);

        MathUtils mathUtils = new MathUtils();
        int multiply = doAction(a, b, mathUtils::multiply);
        System.out.println(a + " * " + b + " = " + multiply);

        String[] stringArray = { "Java", "C++", "PHP", "C#", "Javascript" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        for (String str : stringArray) {
            System.out.println(str);
        }
    }


}
