package bg.softuni.carDealer.services;

import bg.softuni.carDealer.models.Car;
import bg.softuni.carDealer.models.Customer;
import bg.softuni.carDealer.models.Discount;
import bg.softuni.carDealer.models.Sale;
import bg.softuni.carDealer.repositories.CarRepository;
import bg.softuni.carDealer.repositories.CustomerRepository;
import bg.softuni.carDealer.repositories.SaleRepository;
import bg.softuni.carDealer.services.interfaces.SaleService;
import bg.softuni.carDealer.dtos.SaleWithDiscountInfoDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void generateSales() {
        long salesCount = carRepository.count() * 2;

        List<Sale> sales = new ArrayList<>();

        for (int i = 0; i < salesCount; i++) {
            sales.add(createSale());
        }

        saleRepository.saveAll(sales);
    }

    @Override
    public List<SaleWithDiscountInfoDto> getAllSalesWithDiscountInfo() {
        List<Sale> all = saleRepository.findAll();

        List<SaleWithDiscountInfoDto> list = all.stream()
                .map(s -> {
                    SaleWithDiscountInfoDto dto = modelMapper.map(s, SaleWithDiscountInfoDto.class);
                    dto.setPrice(carRepository.findPriceById(s.getCar().getId()));
                    dto.setPriceWithDiscount(s.getDiscount().apply(dto.getPrice()));
                    return dto;
                }).toList();

        return list;
    }

    private Sale createSale() {
        long customerId = ThreadLocalRandom.current().nextLong(customerRepository.count()) + 1;
        Customer customer = customerRepository.findById(customerId).get();
        long carId = ThreadLocalRandom.current().nextLong(carRepository.count()) + 1;
        Car car = carRepository.findById(carId).get();
        int discountIndex = ThreadLocalRandom.current().nextInt(Discount.values().length);
        Discount discount = Discount.values()[discountIndex];

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setCar(car);
        sale.setDiscount(discount);
        return sale;
    }
}
