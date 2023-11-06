package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceExamples {
    public void run4FunctionalInterfaces() {
        final Function<String, Integer> toInt = value -> Integer.parseInt(value);

        final Integer number = toInt.apply("100");
        System.out.println(number);

        final Function<Integer, Integer> identity = Function.identity();

        System.out.println(identity.apply(100));

        final Consumer<String> print = value -> System.out.println(value);

        print.accept("Hello");

        Predicate<Integer> isPositive = i -> i > 0;

        System.out.println(isPositive.test(1));
        System.out.println(isPositive.test(0));
        System.out.println(isPositive.test(-1));

        final List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);

        List<Integer> positiveNumbers = new ArrayList<>();
        for (Integer num : numbers) {
            if (isPositive.test(num)) {
                positiveNumbers.add(num);
            }
        }
        System.out.println("positive integers: " + positiveNumbers);
        System.out.println("positive integers2: " + filter(numbers, isPositive));
        System.out.println("positive integers3: " + filter(numbers, i -> i > 0));

        final Supplier<String> helloSupplier = () -> "Hello ";
        System.out.println(helloSupplier.get() + "world");

        long start = System.currentTimeMillis();
        printIfValidIndex(0, () -> getVeryExpensiveValue());
        printIfValidIndex(-1, () -> getVeryExpensiveValue());
        printIfValidIndex(-2, () -> getVeryExpensiveValue());
        System.out.println("It took " + ((System.currentTimeMillis() - start) / 1000) + " seconds.");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Kevin";
    }

//    private static void printIfValidIndex(int number, String value) {
    private static void printIfValidIndex(int number, Supplier<String> valueSupplier) {
        if (number >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static <T> List filter(List<T> list, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        for (T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        println("Area is ", 12, 20, (message, length, width) -> (message + (length * width)));

        final BigDecimalToCurrency bigDecimalToCurrency = bd -> "$" + bd;
        System.out.println(bigDecimalToCurrency.toCurrency(new BigDecimal("120.00")));

        final InvalidFunctionalInterface invalidFunctionalInterface = new InvalidFunctionalInterface() {
            @Override
            public <T> String toString(T value) {
                return value.toString();
            }
        };

        // 만든 functionalInterface 가 <T> 이기 때문에 타입 추론이 불가능 하여 람다표현식 사용불가. 따라서 타입을 정해주던지 위 방식 처럼 사용해야함
        // InvalidFunctionalInterface invalidFunctionalInterface1 = value -> value.toString();
    }

    private static <T1, T2, T3> void println(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function) {
        System.out.println(function.apply(t1, t2, t3));
    }
}

interface Function3<T1, T2, T3, R> {
    R apply(T1 t1, T2 t2, T3 t3);
}

@FunctionalInterface
interface BigDecimalToCurrency {
    String toCurrency(BigDecimal value);
}

@FunctionalInterface
interface InvalidFunctionalInterface {
    <T> String toString(T value);
}