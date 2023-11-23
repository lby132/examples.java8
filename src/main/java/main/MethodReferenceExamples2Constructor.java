package main;

import java.math.BigDecimal;
import java.util.function.Function;

public class MethodReferenceExamples2Constructor {

    public static void main(String[] args) {
        final Section section1 = new Section(1);

        final Function<Integer, Section> sectionFactoryWithLambdaExpression = number -> new Section(number);
        final Section section1WithLambdaExpression = sectionFactoryWithLambdaExpression.apply(1);

        final Function<Integer, Section> sectionFactoryWithMethodReference = Section::new;
        final Section section1WithMethodReference = sectionFactoryWithMethodReference.apply(1);

        System.out.println(section1);
        System.out.println(section1WithLambdaExpression);
        System.out.println(section1WithMethodReference);

        System.out.println("===================================\n");

        final ProductMethodRef product = new ProductMethodRef(1L, "A", new BigDecimal("100"));
        System.out.println(product);

        final ProductCreator productCreate = ProductMethodRef::new;
        System.out.println(productCreate.create(1L, "A", new BigDecimal("100")));

        System.out.println("===================================\n");
        final ProductA a = createProduct(1L, "A", new BigDecimal("123"), ProductA::new);
        final ProductB b = createProduct(1L, "A", new BigDecimal("123"), ProductB::new);
        final ProductC c = createProduct(1L, "A", new BigDecimal("123"), ProductC::new);

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    private static <T extends ProductMethodRef> T createProduct(final Long id, final String name, final BigDecimal price,
                                                         final ProductCreator<T> productCreator) {
        if (id == null || id < 1L) {
            throw new IllegalArgumentException("The id must be a positive Long.");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The name is not given.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("The price must be greater then 0.");
        }
        return productCreator.create(id, name, price);
    }
}

@FunctionalInterface
interface ProductCreator<T extends ProductMethodRef> {
    T create(Long id, String name, BigDecimal price);
}

class Section {
    private int number;

    public Section(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Section{" +
                "number=" + number +
                '}';
    }
}

class ProductMethodRef {
    private Long id;
    private String name;
    private BigDecimal price;

    public ProductMethodRef(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductMethodRef{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

class ProductA extends ProductMethodRef {

    public ProductA(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "A" + super.toString();
    }
}

class ProductB extends ProductMethodRef {

    public ProductB(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "B" + super.toString();
    }
}

class ProductC extends ProductMethodRef {

    public ProductC(Long id, String name, BigDecimal price) {
        super(id, name, price);
    }

    @Override
    public String toString() {
        return "C" + super.toString();
    }
}