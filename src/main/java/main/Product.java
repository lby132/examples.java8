package main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


class FunctionalInterfaceExamples2 {

    public static void main(String[] args) {
        final Product productA = new Product(1L, "A", new BigDecimal("10.00"));
        final Product productB = new Product(2L, "B", new BigDecimal("55.50"));
        final Product productC = new Product(3L, "C", new BigDecimal("17.45"));
        final Product productD = new Product(4L, "D", new BigDecimal("20.00"));
        final Product productE = new Product(5L, "E", new BigDecimal("110.99"));
        final List<Product> products = Arrays.asList(
                productA,
                productB,
                productC,
                productD,
                productE
        );

        /*List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getPrice().compareTo(new BigDecimal("20")) >= 0) {
                result.add(product);
            }
        }*/
        final BigDecimal twenty = new BigDecimal("20");
        final List<Product> result = filter(products, product -> product.getPrice().compareTo(twenty) > 0);
        System.out.println(result);

        System.out.println("products >= $20: " +
                filter(products, product -> product.getPrice().compareTo(twenty) >= 0));

        System.out.println("products >= $10: " +
                filter(products, product -> product.getPrice().compareTo(new BigDecimal("10")) <= 0));

        final List<Product> expensiveProducts = filter(products, product ->
                product.getPrice().compareTo(new BigDecimal("50")) > 0);

        final List<Product> disCountedProducts = map(filter(products, product ->
                product.getPrice().compareTo(new BigDecimal("50")) > 0), product ->
                new DisCountedProduct(product.getId(), product.getName(), product.getPrice().multiply(new BigDecimal("0.5"))));

        System.out.println("expensiveProducts = " + expensiveProducts);
        System.out.println("disCountedProducts = " + disCountedProducts);

        final Predicate<Product> lessThanOrEqualTo30 = product -> product.getPrice().compareTo(new BigDecimal("30")) <= 0;
        System.out.println("discounted products (<= $30)" +
                 filter(disCountedProducts, lessThanOrEqualTo30));

        System.out.println("           products (<= $30)" +
                 filter(products, lessThanOrEqualTo30));

        /*final List<BigDecimal> prices = map(products, product -> product.getPrice());
        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            total = total.add(price);
        }*/

        final BigDecimal total = total(products, product -> product.getPrice());
        System.out.println("total = " + total);

        final BigDecimal discountedTotal = total(disCountedProducts, product -> product.getPrice());
        System.out.println("discountedTotal = " + discountedTotal);

        final Order order = new Order(1L, "on-1234", Arrays.asList(
                new OrderedItem(1L, productA, 2),
                new OrderedItem(2L, productC, 1),
                new OrderedItem(3L, productD, 10)
        ));

        System.out.println("order.totalPrice() = " + order.totalPrice());
    }

    private static <T> List<T> filter(List<T> list, Predicate<? super T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }

    private static <T> BigDecimal total(List<T> list, Function<T, BigDecimal> mapper) {
        BigDecimal total = BigDecimal.ZERO;
        for (T t : list) {
            total = total.add(mapper.apply(t));
        }

        return total;
    }

    static class Product {

        private Long id;
        private String name;
        private BigDecimal price;

        public Product(Long id, String name, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return new StringBuffer("Product{")
                    .append("id=").append(id)
                    .append(", name='").append(name).append('\'')
                    .append(", price='").append(price)
                    .append('}').toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getPrice(), product.getPrice());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName(), getPrice());
        }
    }

    static class DisCountedProduct extends Product {
        public DisCountedProduct(final Long id, final String name, final BigDecimal price) {
            super(id, name, price);
        }
    }

    static class OrderedItem {
        private Long id;
        private Product product;
        private int quantity;

        public BigDecimal getItemTotal() {
            return product.getPrice().multiply(new BigDecimal(quantity));
        }

        public OrderedItem(Long id, Product product, int quantity) {
            this.id = id;
            this.product = product;
            this.quantity = quantity;
        }
    }

    static class Order {
        private Long id;
        private String orderNumber;
        private List<OrderedItem> items;

        public Order(Long id, String orderNumber, List<OrderedItem> items) {
            this.id = id;
            this.orderNumber = orderNumber;
            this.items = items;
        }

        public BigDecimal totalPrice() {
            return total(items, item -> item.getItemTotal());
        }
    }

}


