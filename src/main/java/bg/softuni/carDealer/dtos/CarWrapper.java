package bg.softuni.carDealer.dtos;

import com.google.gson.annotations.Expose;

public class CarWrapper {
    @Expose
    private CarWithPartsListDto car;

    public CarWrapper() {
    }

    public CarWithPartsListDto getCar() {
        return car;
    }

    public void setCar(CarWithPartsListDto car) {
        this.car = car;
    }
}
