package bg.softuni.carDealer.services.interfaces;

import bg.softuni.carDealer.dtos.CarImportDto;
import bg.softuni.carDealer.dtos.CarWithIdMakeModelTravelDistanceDto;
import bg.softuni.carDealer.dtos.CarWithPartsListDto;
import bg.softuni.carDealer.dtos.CarWrapper;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    void addAll(CarImportDto[] dtos);

    List<CarWithIdMakeModelTravelDistanceDto> getAllToyotaCars();

    List<CarWrapper> getAllCarsWithParts();



}
