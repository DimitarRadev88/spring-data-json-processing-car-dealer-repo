package bg.softuni.carDealer.dtos;

import bg.softuni.carDealer.models.Discount;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaleExportDto {
    @Expose
    @SerializedName(value = "Car")
    private CarExportDto car;
    @Expose
    @SerializedName(value = "Discount")
    private Discount discount;

    public SaleExportDto() {
    }

    public CarExportDto getCar() {
        return car;
    }

    public void setCar(CarExportDto car) {
        this.car = car;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }
}
