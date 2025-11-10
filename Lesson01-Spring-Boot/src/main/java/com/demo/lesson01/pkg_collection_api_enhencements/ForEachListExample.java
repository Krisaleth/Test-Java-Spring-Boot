package com.demo.lesson01.pkg_collection_api_enhencements;

import java.util.Arrays;
import java.util.List;

public class ForEachListExample {
    public static void main(String[] args) {
        List<String> language = Arrays.asList("Java Spring", "C#", "NetCore API", "PHP Laravel", "Javascript");

        System.out.println("Sử dụng biểu thức Lambda: ");
        language.forEach(lang -> System.out.println(lang));

        System.out.println("Sử dụng method rreferences: ");
        language.forEach(System.out::println);
    }
}
