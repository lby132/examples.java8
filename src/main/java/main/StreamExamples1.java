package main;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExamples1 {
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));
//        IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.print(i + " "));
//        Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE))
//                .forEach(i -> System.out.println(i + " "));

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Stream방식은 지연연산으로 15번만 실행됨
        System.out.println("Functional result: " + numbers.stream()
                .filter(n -> n > 3)
                .filter(n -> n < 9)
                .map(n -> n * 2)
                .filter(n -> n > 20)
                .findFirst()
        );

//        final List<Integer> greaterThan3 = filter(numbers, i -> i > 3);
//        final List<Integer> lessThan9 = filter(greaterThan3, i -> i < 9);
//        final List<Integer> doubled = map(lessThan9, i -> i * 2);
//        final List<Integer> greaterThan10 = filter(doubled, i -> i > 10);
//        System.out.println(greaterThan10.get(0));

        // 아래 방식은 27번 실행됨
        final AtomicInteger count = new AtomicInteger(1);

        final List<Integer> greaterThan3 = filter(numbers, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 3");
            return i > 3;
        });
        final List<Integer> lessThan9 = filter(greaterThan3, i -> {
            System.out.println(count.getAndAdd(1) + ": i < 9");
            return i < 9;
        });
        final List<Integer> doubled = map(lessThan9, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 3");
            return i * 2;
        });
        final List<Integer> greaterThan10 = filter(doubled, i -> {
            System.out.println(count.getAndAdd(1) + ": i > 3");
            return i > 10;
        });
        System.out.println("My own method result: " + greaterThan10);
        System.out.println("greaterThan10.get(0) = " + greaterThan10.get(0));

        System.out.println("==========================================");

        final List<Integer> myOwnMethodResult =
                filter(
                        map(
                                filter(
                                        filter(numbers,
                                                i -> i > 3),
                                        i -> i < 9),
                                i -> i * 2),
                        i -> i > 10);
        System.out.println("myOwnMethodResult = " + myOwnMethodResult);
        System.out.println("myOwnMethodResult.get(0) = " + myOwnMethodResult.get(0));

    }

    private static <T> List<T> filter(final List<T> list, Predicate<T> predicate) {
        final List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }

    private static <T, R> List<R> map(final List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
