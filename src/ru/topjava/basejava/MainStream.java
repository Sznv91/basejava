package ru.topjava.basejava;

/*
Task
I
реализовать метод через стрим int minValue(int[] values).
Метод принимает массив цифр от 1 до 9, надо выбрать уникальные и вернуть минимально возможное число,
составленное из этих уникальных цифр. Не использовать преобразование в строку и обратно.
Например {1,2,3,3,2,3} вернет 123, а {9,8} вернет 89

II
реализовать метод List<Integer> oddOrEven(List<Integer> integers) если сумма всех чисел нечетная - удалить все нечетные,
 если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.
*/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String... args) {
        MainStream instance = new MainStream();
        int[] testMassive = new int[]{1, 2, 3, 3, 2, 3};
//        int[] testMassive = new int[]{9, 8};
        Long startTimerMinValue = new Date().getTime();
        int toPrint = instance.minValue(testMassive);
        System.out.println(toPrint + " minValue Method Result");
        Long endTimerMinValue = new Date().getTime();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i : testMassive) list.add(i);

        Long startTimerOddOrEven = new Date().getTime();
        System.out.println(instance.oddOrEven(list) + " oddOrEven Method Result");
        Long endTimerOddOrEven = new Date().getTime();

        Long startTimerMinValueStream = new Date().getTime();
        System.out.println(instance.minValueStream(testMassive) + " minValue Stream Result");
        Long endTimerMinValueStream = new Date().getTime();

        Long startTimerStringOddOrEven = new Date().getTime();
        System.out.println(instance.oddOrEvenStream(list) + " oddOrEven Stream Result");
        Long endTimerStringOddOrEven = new Date().getTime();

        System.out.println("minValue Methods timer is " + (endTimerMinValue - startTimerMinValue)
                + "; minValue Stream timer is " + (endTimerMinValueStream - startTimerMinValueStream)
                + "\noddOrEven Methods timer is " + (endTimerOddOrEven - startTimerOddOrEven)
                + "; oddOrEven Stream timer is " + (endTimerStringOddOrEven - startTimerStringOddOrEven));
    }

    // to use https://ru.stackoverflow.com/a/580217

    private int minValue(int[] values) {
        Arrays.sort(values);
        int counter = 0;
        int[] tmp = new int[values.length];
        for (int i : values) {
            if (!contain(tmp, i)) {
                tmp[counter] = i;
                counter++;
            }
        }
        tmp = Arrays.copyOfRange(tmp, 0, counter);
        return toInt(tmp);
    }

    private boolean contain(int[] massive, int searchableNumber) {
        for (int num : massive) {
            if (searchableNumber == num) {
                return true;
            }
        }
        return false;
    }

    private int toInt(int[] massive) {
        int result = 0;
        for (int i = 0; i < massive.length; i++) {
            int counter = (massive.length - i) - 1;
            int q = massive[i] * (int) (Math.pow(10, counter));
            result += q;
        }
        return result;
    }

    private List<Integer> oddOrEven(List<Integer> integers) {
        List<Integer> result = new ArrayList<>(integers);
        int sum = sumList(result);
        boolean even = isEven(sum);
        if (even) {
            for (int i = 0; i < result.size(); i++) {
                if (isEven(result.get(i))) {
                    result.remove(i);
                    i--;
                }
            }
        } else {
            for (int i = 0; i < result.size(); i++) {
                if (!isEven(result.get(i))) {
                    result.remove(i);
                    i--;
                }
            }
        }

        return result;
    }

    private boolean isEven(int number) {
        return (number % 2 == 0);
    }

    private int sumList(List<Integer> list) {
        AtomicInteger sum = new AtomicInteger();
        list.forEach(integer -> sum.set(sum.get() + integer));
        return sum.get();
    }

    private int minValueStream(int[] values) {
        values = Arrays.stream(values).sorted().distinct().toArray();
        return toInt(values);
    }

    private List<Integer> oddOrEvenStream(List<Integer> integers) {
        int reduce = integers.stream().reduce(Integer::sum).orElse(0);

        if (reduce % 2 == 0) {
            return integers.stream().filter(o -> o % 2 != 0).collect(Collectors.toList());
        } else {
            return integers.stream().filter(o -> o % 2 == 0).collect(Collectors.toList());
        }
    }
}
