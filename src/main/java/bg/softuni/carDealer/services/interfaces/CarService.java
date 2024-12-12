package bg.softuni.carDealer.services.interfaces;

import bg.softuni.carDealer.dots.CarImportDto;

public interface CarService {
    void addAll(CarImportDto[] dtos);
}
