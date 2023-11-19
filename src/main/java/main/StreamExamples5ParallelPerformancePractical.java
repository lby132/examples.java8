package main;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamExamples5ParallelPerformancePractical {
    private static final String[] priceStrings = {"1.0", "100.99", "35.75", "21.30", "88.00"};
    private static final BigDecimal[] targetPrices = {new BigDecimal("30"), new BigDecimal("20"), new BigDecimal("31")};
    private static final Random random = new Random(123);
    private static final Random targetPriceRandom = new Random(111);

    private static final List<ProductExample5> products;
    
    static {
        final int length = 8_000_000;
//        final List<ProductExample5> list = new ArrayList<>();
        final ProductExample5[] list = new ProductExample5[length];

        for (int i = 1; i <= length; i++) {
            list[i - 1] = new ProductExample5((long) i, "Product" + i, new BigDecimal(priceStrings[random.nextInt(5)]));
        }
//        products = Collections.unmodifiableList(list);
        products = Arrays.asList(list);
    }

    private static BigDecimal imperativeSum(final List<ProductExample5> products, final Predicate<ProductExample5> predicate) {
        BigDecimal sum = BigDecimal.ZERO;
        for (ProductExample5 product : products) {
            if (predicate.test(product)) {
                sum = sum.add(product.getPrice());
            }
        }
        return sum;
    }

    private static BigDecimal streamSum(final Stream<ProductExample5> stream, final Predicate<ProductExample5> predicate) {
        return stream.filter(predicate).map(ProductExample5::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private static void imperativeTest(BigDecimal targetPrice) {
        System.out.println("==========================================");
        System.out.println("\nImperative Sum\n--------------------------------------------------");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("==========================================");
    }
    private static void streamTest(BigDecimal targetPrice) {
        System.out.println("==========================================");
        System.out.println("\nImperative Sum\n--------------------------------------------------");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("==========================================");
    }
    private static void parallelStreamTest(BigDecimal targetPrice) {
        System.out.println("==========================================");
        System.out.println("\nImperative Sum\n--------------------------------------------------");
        final long start = System.currentTimeMillis();
        System.out.println("Sum: " +
                imperativeSum(products, product -> product.getPrice().compareTo(targetPrice) >= 0)
        );
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms.");
        System.out.println("==========================================");
    }


    public static void main(String[] args) {
        final BigDecimal targetPrice = new BigDecimal("40");

        imperativeTest(targetPrice);
        streamTest(targetPrice);
        parallelStreamTest(targetPrice);

        System.out.println("\nIgnore TEsts Above\n=============================\n");
        System.out.println("Start!");
        for (int i = 0; i < 5; i++) {
            BigDecimal price = targetPrices[targetPriceRandom.nextInt(3)];

            imperativeTest(price);
            streamTest(price);
            parallelStreamTest(price);
        }
    }
}

class ProductExample5 {
    private Long id;
    private String name;
    private BigDecimal price;

    public ProductExample5(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
