package main;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamExamples5Parallel {

    public static void main(String[] args) {
        final int[] sum = {0};
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);

        System.out.println("stream sum: " + sum[0]);

        final int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);

        System.out.println("parallel sum (with side-effect): " + sum2[0]);

        System.out.println("stream sum (no side-effect): " +
                IntStream.range(0, 100)
                        .sum());

        System.out.println("parallel stream sum (no side-effect): " +
                IntStream.range(0, 100)
                        .parallel()
                        .sum());

        System.out.println("\n=======================================");
        System.out.println("stream");
        final long start = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start);

        // 테스트중 컴퓨터는 8코어
        System.out.println("\n=======================================");
        System.out.println("parallel stream (8 elements)");
        final long start2 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start2);

        System.out.println("\n=======================================");
        System.out.println("parallel stream (9 elements)");
        final long start3 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start3);

        System.out.println("\n=======================================");
        System.out.println("parallel stream (8 elements) with parallelism: 7");
        // 0: 코어 개수 = 1, 1: 2, 3: 4, 7: 8
        // 코어 8개를 사용하기 위해 setProperty() 마지막 변수값에 "7"
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "7");
        final long start4 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start4);

        System.out.println("\n=======================================");
        System.out.println("parallel stream (8 elements) with parallelism: 3");
        // 0: 코어 개수 = 1, 1: 2, 3: 4, 7: 8
        // 코어 4개를 사용하기 위해 setProperty() 마지막 변수값에 "3"
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");
        final long start5 = System.currentTimeMillis();
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - start5);
    }
}
