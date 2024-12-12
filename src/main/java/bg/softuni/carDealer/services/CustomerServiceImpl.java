package bg.softuni.carDealer.services;

import bg.softuni.carDealer.dtos.CustomerExportDto;
import bg.softuni.carDealer.dtos.CustomerImportDto;
import bg.softuni.carDealer.dtos.CustomerWithTotalSalesDto;
import bg.softuni.carDealer.models.Customer;
import bg.softuni.carDealer.repositories.CustomerRepository;
import bg.softuni.carDealer.repositories.SaleRepository;
import bg.softuni.carDealer.services.interfaces.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final SaleRepository saleRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper,
                               SaleRepository saleRepository) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.saleRepository = saleRepository;
    }

    @Override
    public void addAll(CustomerImportDto[] dtos) {
        List<Customer> list = Arrays.stream(dtos)
                .map(dto -> modelMapper.map(dto, Customer.class))
                .toList();

        customerRepository.saveAll(list);
    }

    @Override
    public List<CustomerExportDto> getAllCustomersSortedByBirthDate() {
        List<Customer> customers = customerRepository
                .findAllOrderByBirthDateAndYoungDriver();

        return customers
                .stream()
                .map(c -> modelMapper.map(c, CustomerExportDto.class))
                .toList();
    }

    @Override
    public List<CustomerWithTotalSalesDto> getAllCustomersWithAtLeastOneCarSale() {
        List<Customer> customers = customerRepository.findAllSaleNotEmpty();

        List<CustomerWithTotalSalesDto> list = customers
                .stream()
                .map(c -> {
                    CustomerWithTotalSalesDto dto = modelMapper.map(c, CustomerWithTotalSalesDto.class);
                    dto.setBoughtCars(c.getSales().size());
                    dto.setSpentMoney(saleRepository.getSpentMoneyByCustomerId(c.getId()));
                    return dto;
                })
                .sorted(Comparator
                        .comparingInt(CustomerWithTotalSalesDto::getBoughtCars)
                        .thenComparing(CustomerWithTotalSalesDto::getSpentMoney).reversed()
                ).toList();

        return list;
    }
}
