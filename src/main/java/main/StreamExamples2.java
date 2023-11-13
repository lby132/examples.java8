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

        final Integer integer127 = 127;
        System.out.println(Stream.of(1, 3, 3, 5, 127)
                .filter(i -> i == integer127)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(joining(", ", "[", "]")));

        // Stream.of안에 있는 값들은 Integer.valueOf()로 기본타입에서 객체타입으로 오토박싱 되는데 -128에서 127까지 캐시가 되기때문에
        // 127까지는 같은 객체로 ==으로 비교해도 맞게 나오지만 127이후에 128부터는 다른객체로 나오기 때문에 ==이 아닌 equals메소드를 써야함
        final Integer integer128 = 128;
        System.out.println(Stream.of(1, 3, 3, 5, 128)
                .filter(i -> i == integer128)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(joining(", ", "[", "]")));

        System.out.println(Stream.of(1, 3, 3, 5, 128)
                .filter(i -> i.equals(integer128))
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(joining(", ", "[", "]")));

        Integer integer3 = 3;
        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i > integer3)
                .count());
    }
}
