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


public class MainStream {

    public static void main(String... args) {
        MainStream instance = new MainStream();
        instance.minValue(new int[]{1, 2, 3, 3, 2, 3});
    }

    private int minValue(int[] values) {
        int result = 0;
        // to use https://ru.stackoverflow.com/a/580217
        return result;
    }
}
