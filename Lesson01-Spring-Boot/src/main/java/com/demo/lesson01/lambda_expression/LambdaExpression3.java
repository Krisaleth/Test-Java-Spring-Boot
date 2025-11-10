package com.demo.lesson01.lambda_expression;

@FunctionalInterface
interface Calc1 {
    int add(int a, int b);
}

interface Calc2 {
    void add(int a, int b);
}

public class LambdaExpression3 {
    public static void main(String[] args) {
        Calc1 calc1 = (int a, int b) -> (a+b);
        System.out.println(calc1.add(2, 3));

        Calc1 calc2 = (a,b) -> (a+b);
        System.out.println(calc2.add(10, 44));

        Calc2 calc3 = (a,b) -> System.out.println(a+b);
        calc3.add(33, 62);
    }
}
