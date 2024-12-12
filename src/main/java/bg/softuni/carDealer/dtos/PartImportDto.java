package bg.softuni.carDealer.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartImportDto {

    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Integer quantity;

    public PartImportDto() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
