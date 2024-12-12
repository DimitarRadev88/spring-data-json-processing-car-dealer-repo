package bg.softuni.carDealer.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class CarWithPartsListDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;
    @Expose
    List<PartWithNameAndPriceDto> parts;

    public CarWithPartsListDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartWithNameAndPriceDto> getParts() {
        return parts;
    }

    public void setParts(List<PartWithNameAndPriceDto> parts) {
        this.parts = parts;
    }
}
