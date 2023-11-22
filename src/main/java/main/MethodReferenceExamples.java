package main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class MethodReferenceExamples {

    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);

        //BigDecimal 자체가 compare 로 자동정렬 기능이 있지만 테스트용으로 구현
        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
//                        .sorted((bd1, bd2) -> bd1.compareTo(bd2))
                        .sorted(BigDecimalUtil::compare)
                        .collect(toList()
                        ));

        final String targetString = "c";
        System.out.println(
                Arrays.asList("a", "b", "c", "d")
                        .stream()
                        .anyMatch(targetString::equals)
        );
    }

}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}
