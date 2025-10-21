package com.demo.lesson01.tight_loosely_coupling;

import java.util.Arrays;

interface SortAlgorithm {
    void sort(int[] arr);
}

class LooselyBubbleSortAlgorithm implements SortAlgorithm {
    @Override
    public void sort(int[] arr) {
        System.out.println("Sorted suing bubble sort algorithm: ");

        Arrays.stream(arr).sorted().forEach(System.out::println);
    }
}

public class LooselyCoupledService {
    private SortAlgorithm sortAlgorithm;
    public LooselyCoupledService() {}
    public LooselyCoupledService(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm =sortAlgorithm;
    }
    public void complexBusiness(int[] arr) {
        sortAlgorithm.sort(arr);
    }

    public static void main(String[] args) {
        LooselyCoupledService sortAlgorithm = new LooselyCoupledService();
        sortAlgorithm.complexBusiness(new int[] {11, 21, 13, 42, 15});
    }
}


