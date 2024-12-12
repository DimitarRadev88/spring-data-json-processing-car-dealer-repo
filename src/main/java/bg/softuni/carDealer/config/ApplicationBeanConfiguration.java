package bg.softuni.carDealer.config;

import bg.softuni.carDealer.dtos.*;
import bg.softuni.carDealer.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class ApplicationBeanConfiguration {

    private ModelMapper modelMapper;
    private Gson gson;

    @Bean
    public ModelMapper getModelMapperInstance() {
        if (modelMapper == null) {
            modelMapper = new ModelMapper();

            addMappings();
        }

        return modelMapper;
    }

    private void addMappings() {
        customerImportDtoToCustomerTypeMap();
        supplierToSupplierWithIdNameAndPartsCountTypeMap();
        catToCarWithPartsListDtoTypeMap();
        saleToSaleWithDiscountInfoDtoTypeMap();


    }

    private void saleToSaleWithDiscountInfoDtoTypeMap() {
        Converter<Discount, BigDecimal> enumToValue = c -> c.getSource() == null
                ? null
                : c.getSource().getPercent();

        modelMapper.createTypeMap(Sale.class, SaleWithDiscountInfoDto.class)
                .addMappings(mapper -> mapper.
                        using(enumToValue)
                        .map(Sale::getDiscount, SaleWithDiscountInfoDto::setDiscount));
    }

    private void catToCarWithPartsListDtoTypeMap() {
        Converter<List<Part>, List<PartWithNameAndPriceDto>> partListToPartWithNameAndPriceList = c -> c.getSource() == null
                ? null
                : c.getSource().stream().map(p -> modelMapper.map(p, PartWithNameAndPriceDto.class)).toList();

        modelMapper.createTypeMap(Car.class, CarWithPartsListDto.class)
                .addMappings(mapper -> mapper.using(partListToPartWithNameAndPriceList).map(Car::getParts, CarWithPartsListDto::setParts));
    }

    private void supplierToSupplierWithIdNameAndPartsCountTypeMap() {
        Converter<List<String>, Integer> partsToCountConverter = c -> c.getSource() == null
                ? null
                : c.getSource().size();

        modelMapper
                .createTypeMap(Supplier.class, SupplierWithIdNameAndPartsCountDto.class)
                .addMappings(mapper -> mapper
                        .using(partsToCountConverter)
                        .map(Supplier::getParts, SupplierWithIdNameAndPartsCountDto::setPartsCount));


    }

    private void customerImportDtoToCustomerTypeMap() {
        Converter<String, LocalDateTime> dateConverter = c -> c.getSource() == null ? null
                : LocalDateTime.parse(c.getSource(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        modelMapper.createTypeMap(CustomerImportDto.class, Customer.class)
                .addMappings(mapper -> mapper.using(dateConverter)
                        .map(CustomerImportDto::getBirthDate, Customer::setBirthDate));
    }

    @Bean
    public Gson getGsonInstance() {
        if (gson == null) {
            gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .setPrettyPrinting().create();
        }

        return gson;
    }

}
