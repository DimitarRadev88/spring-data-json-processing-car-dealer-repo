package bg.softuni.carDealer.dtos;

import com.google.gson.annotations.Expose;

public class CustomerImportDto {

    @Expose
    private String name;
    @Expose
    private String birthDate;
    @Expose
    private Boolean isYoungDriver;

    public CustomerImportDto() {
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
}
