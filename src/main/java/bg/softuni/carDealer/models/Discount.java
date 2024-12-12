package bg.softuni.carDealer.models;

import java.math.BigDecimal;

public enum Discount {
    PERCENT0("0.0"),
    PERCENT5("0.5"),
    PERCENT10("0.10"),
    PERCENT15("0.15"),
    PERCENT20("0.20"),
    PERCENT30("0.30"),
    PERCENT40("0.40"),
    PERCENT50("0.50");

    private final BigDecimal percent;

    Discount(String percent) {
        this.percent = new BigDecimal(percent);
    }

    public BigDecimal apply(BigDecimal price) {
        return price.subtract(price.multiply(price));
    }

}
