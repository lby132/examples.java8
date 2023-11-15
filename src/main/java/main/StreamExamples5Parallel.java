package main;

import java.util.stream.IntStream;

public class StreamExamples5Parallel {

    public static void main(String[] args) {
        final int[] sum = {0};
        IntStream.range(0, 100)
                .forEach(i -> sum[0] += i);

        System.out.println("sum: " + sum[0]);

        final int[] sum2 = {0};
        IntStream.range(0, 100)
                .parallel()
                .forEach(i -> sum2[0] += i);

        System.out.println("parallel sum (with side-effect): " + sum2[0]);
    }
}
