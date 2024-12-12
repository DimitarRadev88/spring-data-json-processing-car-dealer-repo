package bg.softuni.carDealer.services;

import bg.softuni.carDealer.dtos.CarImportDto;
import bg.softuni.carDealer.dtos.CarWithIdMakeModelTravelDistanceDto;
import bg.softuni.carDealer.dtos.CarWithPartsListDto;
import bg.softuni.carDealer.dtos.CarWrapper;
import bg.softuni.carDealer.models.Car;
import bg.softuni.carDealer.models.Part;
import bg.softuni.carDealer.repositories.CarRepository;
import bg.softuni.carDealer.repositories.PartRepository;
import bg.softuni.carDealer.services.interfaces.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

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

    @Override
    public List<CarWithIdMakeModelTravelDistanceDto> getAllToyotaCars() {
        return carRepository
                .findAllByMakeEqualsOrderByModelAscTravelledDistanceDesc("Toyota")
                .stream()
                .map(c -> modelMapper.map(c, CarWithIdMakeModelTravelDistanceDto.class))
                .toList();
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

    @Override
    public List<CarWrapper> getAllCarsWithParts() {

        List<CarWrapper> cars = carRepository
                .findAllWithParts()
                .stream()
                .map(c -> {
                    CarWrapper wrapper = new CarWrapper();
                    wrapper.setCar(modelMapper.map(c, CarWithPartsListDto.class));
                    return wrapper;
                })
                .toList();

        return cars;
    }


}
