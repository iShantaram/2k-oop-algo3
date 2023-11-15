package pro.sky.agapov;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int count = 100_000;
        ArrListImpl arrList = new ArrListImpl(count);
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            arrList.add(random.nextInt(-100_000, 100_000));
        }

        System.out.println("Обычный поиск по элементу:");
        if (arrList.contains(0)) {
            System.out.println("0 содержит");
            System.out.println("0 - " + arrList.indexOf(0) + "-ый элемент");
        } else {
            System.out.println("0 не содержит");
        }

        // После использования метода binaryContains внутренний массив будет отсортирован приватным методом qSort
        System.out.println("Бинарный поиск по элементу:");
        if (arrList.binaryContains(0)) {
            System.out.println("0 содержит");
            System.out.println("0 - " + arrList.indexOf(0) + "-ый элемент в упорядоченном массиве");
        } else {
            System.out.println("0 не содержит");
        }
    }
}