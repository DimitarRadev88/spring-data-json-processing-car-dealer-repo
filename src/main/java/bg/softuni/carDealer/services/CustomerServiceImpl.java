package bg.softuni.carDealer.services;

import bg.softuni.carDealer.dots.CustomerImportDto;
import bg.softuni.carDealer.models.Customer;
import bg.softuni.carDealer.repositories.CustomerRepository;
import bg.softuni.carDealer.services.interfaces.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addAll(CustomerImportDto[] dtos) {
        List<Customer> list = Arrays.stream(dtos)
                .map(dto -> modelMapper.map(dto, Customer.class))
                .toList();

        customerRepository.saveAll(list);
    }
}
