package bg.softuni.carDealer.services;

import bg.softuni.carDealer.dtos.PartImportDto;
import bg.softuni.carDealer.models.Part;
import bg.softuni.carDealer.models.Supplier;
import bg.softuni.carDealer.repositories.PartRepository;
import bg.softuni.carDealer.repositories.SupplierRepository;
import bg.softuni.carDealer.services.interfaces.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PartServiceImpl implements PartService {

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository, ModelMapper modelMapper) {
        this.partRepository = partRepository;
        this.supplierRepository = supplierRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAll(PartImportDto[] dtos) {
        List<Part> list = Arrays.stream(dtos).map(dto -> {
            Part part = modelMapper.map(dto, Part.class);
            part.setSupplier(getRandomSupplier());
            return part;
        }).toList();

        partRepository.saveAll(list);
    }

    private Supplier getRandomSupplier() {
        long id = ThreadLocalRandom.current().nextLong(supplierRepository.count()) + 1;

        return supplierRepository.findById(id).orElse(null);
    }
}
