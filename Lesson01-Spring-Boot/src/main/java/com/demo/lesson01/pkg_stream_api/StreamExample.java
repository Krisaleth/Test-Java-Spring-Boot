package com.demo.lesson01.pkg_stream_api;

import java.util.Arrays;
import java.util.List;

public class StreamExample {
    List<Integer> intList = Arrays.asList(11, 22, 55, 33, 44, 66);
    public void withoutStream() {
        int count = 0;
        for (Integer integer : intList) {
            if (integer % 2 == 0) {
                count++;
            }
        }
        System.out.println("WithoutStream -> Số phần tử chẵn: " + count);
    }
    public void withStream() {
        long count = intList.stream().filter(num -> num % 2 == 0).count();
        System.out.println("WithStream -> Số phần tử chẵn: " + count);
    }

    public static void main(String[] args) {
        StreamExample streamExample = new StreamExample();
        streamExample.withoutStream();
        streamExample.withStream();
    }
}
