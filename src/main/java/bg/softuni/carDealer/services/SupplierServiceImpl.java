package bg.softuni.carDealer.services;

import bg.softuni.carDealer.dots.SupplierImportDto;
import bg.softuni.carDealer.models.Supplier;
import bg.softuni.carDealer.repositories.SupplierRepository;
import bg.softuni.carDealer.services.interfaces.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAll(SupplierImportDto[] dtos) {
        List<Supplier> list = Arrays.stream(dtos)
                .map(dto -> modelMapper.map(dto, Supplier.class))
                .toList();

        supplierRepository.saveAll(list);
    }
}
