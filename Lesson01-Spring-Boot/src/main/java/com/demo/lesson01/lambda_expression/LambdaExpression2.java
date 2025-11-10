package com.demo.lesson01.lambda_expression;

@FunctionalInterface
interface SayHello2 {
    public void sayHello(String name);
}

public class LambdaExpression2 {
    public static void main(String[] args) {
        SayHello2 say1 = (name) -> {
            System.out.println("Hello " + name);
        };

        say1.sayHello("XCode");

        SayHello2 say2 = name -> {
            System.out.println("Hello " + name);
        };

        say2.sayHello("Kris");

        SayHello2 say3 = name -> System.out.println("Hello " + name);

        say3.sayHello("Krisaleth");
    }
}
