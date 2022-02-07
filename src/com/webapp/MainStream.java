package com.webapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(minValue(new int[]{9, 8}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6)));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 6)));
    }

    /*
    Реализовать метод через стрим int minValue(int[] values).
    Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
    составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
    Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89
    */
    public static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (x, y) -> x * 10 + y);
    }

    /*
    Реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все
    нечетные, если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
    */
    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().reduce(0, Integer::sum);
        List<Integer> newIntegers = new ArrayList<>(integers);
        newIntegers.removeIf(el -> ((el % 2) == (sum % 2)));
        /*newIntegers = integers.stream().filter(el-> (el % 2) != (sum % 2)).collect(Collectors.toList());*/
        return newIntegers;
    }
}
