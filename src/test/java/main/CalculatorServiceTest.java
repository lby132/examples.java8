package main;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class CalculatorServiceTest {

    @Test
    void testCalculateAddition() throws Exception {
        final CalculatorService calculatorService = new CalculatorService(new Addition() ,new Subtraction());

        final int actual = calculatorService.calculate(1, 1);

        Assertions.assertThat(actual).isEqualTo(2);

    }

    @Test
    void testCalculateSubtraction() throws Exception {
        final CalculatorService calculatorService = new CalculatorService(new Subtraction(), new Subtraction());

        final int actual = calculatorService.calculate(3, 1);

        Assertions.assertThat(actual).isEqualTo(2);

    }

    @Test
    void testCalculateMultiplication() throws Exception {
        final CalculatorService calculatorService = new CalculatorService(new Multiplication(), new Subtraction());

        final int actual = calculatorService.calculate(1, 1);

        Assertions.assertThat(actual).isEqualTo(1);

    }

    @Test
    void testCalculateDivision() throws Exception {
        final CalculatorService calculatorService = new CalculatorService(new Division(), new Subtraction());

        final int actual = calculatorService.calculate(1, 1);

        Assertions.assertThat(actual).isEqualTo(1);

    }

}