package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StreamPrelude {

    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);

        System.out.println("abs1 == abs2 is " + (abs1 == abs2));

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(
                map(numbers, i -> i * 2)
        );
        //람다를 사용하여 identity function 을 해줌으로써 null 신경쓰지 않아도 됨
        System.out.println(
                map(numbers, i -> i)
        );
        //Function.identity() 를 사용하여 null 신경쓰지 않아도 됨
        System.out.println(
                map(numbers, Function.identity())
        );
    }

    private static <T, R> List<R> map(final List<T> list, final Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(mapper.apply(t));
        }

        return result;
    }
}
