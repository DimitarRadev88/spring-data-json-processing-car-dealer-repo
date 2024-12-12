package bg.softuni.carDealer.services.interfaces;

import bg.softuni.carDealer.dtos.SupplierImportDto;
import bg.softuni.carDealer.dtos.SupplierWithIdNameAndPartsCountDto;

import java.util.List;

public interface SupplierService {
    void addAll(SupplierImportDto[] dtos);

    List<SupplierWithIdNameAndPartsCountDto> getAllLocalSuppliers();
}
