package main;

public class OopAndFpExamples {
    public static void main(String[] args) {
        final CalculatorService calculatorService = new CalculatorService(new Division(), new Subtraction());

        final int additionResult = calculatorService.calculate(11, 1);
        System.out.println(additionResult);

        final int subtractionResult = calculatorService.calculate(11, 1);
        System.out.println(subtractionResult);

        final int multiplicationResult = calculatorService.calculate(11, 1);
        System.out.println(multiplicationResult);

        final int divisionResult = calculatorService.calculate(18, 4);
        System.out.println(divisionResult);

        final FunctionalCalculatorService functionalCalculatorService = new FunctionalCalculatorService();
        System.out.println(functionalCalculatorService.calculate((i1, i2) -> i1 + i2, 11, 4));
        System.out.println(functionalCalculatorService.calculate((i1, i2) -> i1 - i2, 15, 6));
        System.out.println(functionalCalculatorService.calculate((i1, i2) -> i1 * i2, 15, 6));
        System.out.println(functionalCalculatorService.calculate((i1, i2) -> i1 / i2, 15, 6));
    }
}

interface Calculation {
    int calculate(int num1, int num2);
}

class Addition implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplication implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}

class Division implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}


class CalculatorService {

    private final Calculation calculation;
    private final Calculation calculation2;

    public CalculatorService(Calculation calculation, Calculation calculation2) {
        this.calculation = calculation;
        this.calculation2 = calculation2;
    }

    public int calculate(int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2);
        }
    }

    public int compute(int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation2.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2);
        }
    }

}

class FunctionalCalculatorService {
    public int calculate(Calculation calculation, int num1, int num2) {
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid input num1: " + num1 + ", num2: " + num2);
        }
    }
}

