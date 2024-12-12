package bg.softuni.carDealer.services;

import bg.softuni.carDealer.dots.CarImportDto;
import bg.softuni.carDealer.models.Car;
import bg.softuni.carDealer.models.Part;
import bg.softuni.carDealer.repositories.CarRepository;
import bg.softuni.carDealer.repositories.PartRepository;
import bg.softuni.carDealer.services.interfaces.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAll(CarImportDto[] dtos) {
        List<Car> list = Arrays.stream(dtos)
                .map(dto -> {
                    Car car = modelMapper.map(dto, Car.class);
                    car.setParts(List.copyOf(getRandomParts()));
                    return car;
                })
                .toList();

        carRepository.saveAll(list);
    }

    private Set<Part> getRandomParts() {
        int count = ThreadLocalRandom.current().nextInt(3) + 3;

        Set<Part> parts = new HashSet<>();

        while (parts.size() < count) {
            long id = ThreadLocalRandom.current().nextLong(partRepository.count()) + 1;
            parts.add(partRepository.findById(id).orElse(null));
        }

        return parts;
    }
}
