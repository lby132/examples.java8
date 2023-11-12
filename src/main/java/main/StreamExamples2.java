package main;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExamples2 {
    public static void main(String[] args) {
        System.out.println(Stream.of(1, 3, 3, 4, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toList()));

        System.out.println(Stream.of(1, 3, 3, 4, 5)
                .filter(i -> i > 3)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toSet()));

        System.out.println(Stream.of(1, 3, 3, 4, 5)
                .filter(i -> i > 3)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining()));

        System.out.println(Stream.of(1, 3, 3, 4, 5)
                .filter(i -> i > 3)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining(", ")));

        System.out.println(Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 3)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining(", ", "[", "]")));

        System.out.println(Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 3)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(joining(", ", "[", "]")));
    }
}
