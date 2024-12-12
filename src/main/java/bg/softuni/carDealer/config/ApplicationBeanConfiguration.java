package bg.softuni.carDealer.config;

import bg.softuni.carDealer.dots.CustomerImportDto;
import bg.softuni.carDealer.models.Customer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
