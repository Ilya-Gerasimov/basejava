package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
        реализовать метод через стрим int minValue(int[] values).
        Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
        составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
        Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

        реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная -
        удалить все нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N).
        Optional - решение в один стрим.
*/

public class MainStream {

    public static int minValue(int[] values) {
        return IntStream.of(values)
                .distinct()
                .sorted()
                .map(x -> (int) (x * Math.pow(10, IntStream.of(values).distinct().count() - x)))//тут для первого элемента должна быть 1, потом 2...
                .sum();
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        return integers.stream()
                .filter(value -> value % 2 == integers.stream().mapToInt(num -> num).sum() % 2)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] values1 = {1, 2, 3, 3, 2, 3};
        System.out.println(minValue(values1));

        int[] values2 = {9, 8};
        System.out.println(minValue(values2));

        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(5);
        integers.add(3);
        integers.add(7);
        System.out.println(oddOrEven(integers));

        List<Integer> integers1 = new ArrayList<>();
        integers1.add(4);
        integers1.add(6);
        integers1.add(1);
        integers1.add(4);
        integers1.add(3);
        integers1.add(4);
        System.out.println(oddOrEven(integers1));

    }
}
