package bg.softuni.carDealer.services.interfaces;

import bg.softuni.carDealer.dtos.SaleWithDiscountInfoDto;

import java.util.List;

public interface SaleService {
    void generateSales();

    List<SaleWithDiscountInfoDto> getAllSalesWithDiscountInfo();
}
