package bg.softuni.carDealer.services.interfaces;

import bg.softuni.carDealer.dots.CustomerImportDto;

public interface CustomerService {
    void addAll(CustomerImportDto[] dtos);
}
