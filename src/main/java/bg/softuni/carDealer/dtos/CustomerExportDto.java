package bg.softuni.carDealer.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CustomerExportDto {
    @Expose
    @SerializedName(value = "Id")
    private Long id;
    @Expose
    @SerializedName(value = "Name")
    private String name;
    @Expose
    @SerializedName(value = "BirthDate")
    private String birthDate;
    @Expose
    @SerializedName(value = "IsYoungDriver")
    private Boolean isYoungDriver;
    @Expose
    @SerializedName(value = "Sales")
    private List<SaleExportDto> sales;

    public CustomerExportDto() {
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public List<SaleExportDto> getSales() {
        return sales;
    }

    public void setSales(List<SaleExportDto> sales) {
        this.sales = sales;
    }
}
