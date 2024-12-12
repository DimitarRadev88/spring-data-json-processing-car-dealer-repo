package bg.softuni.carDealer.services.interfaces;

import bg.softuni.carDealer.dtos.CarExportDto;
import bg.softuni.carDealer.dtos.CustomerExportDto;
import bg.softuni.carDealer.dtos.CustomerImportDto;
import bg.softuni.carDealer.dtos.CustomerWithTotalSalesDto;

import java.util.List;

public interface CustomerService {
    void addAll(CustomerImportDto[] dtos);

    List<CustomerExportDto> getAllCustomersSortedByBirthDate();

    List<CustomerWithTotalSalesDto> getAllCustomersWithAtLeastOneCarSale();
}
