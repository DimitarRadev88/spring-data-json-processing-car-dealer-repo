package bg.softuni.carDealer.core;

import bg.softuni.carDealer.dtos.*;
import bg.softuni.carDealer.services.interfaces.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String JSON_IMPORT_FOLDER_PATH = "src/main/resources/jsonFiles/input/";
    private static final String JSON_EXPORT_FOLDER_PATH = "src/main/resources/jsonFiles/output/";
    private static final String SUPPLIERS_JSON_NAME = "suppliers.json";
    private static final String CARS_JSON_NAME = "cars.json";
    private static final String PARTS_JSON_NAME = "parts.json";
    private static final String CUSTOMERS_JSON_NAME = "customers.json";
    private final CarService carService;
    private final CustomerService customerService;
    private final PartService partService;
    private final SupplierService supplierService;
    private final SaleService saleService;
    private final Gson gson;

    @Autowired
    public ConsoleRunner(CarService carService, CustomerService customerService, PartService partService, SupplierService supplierService, SaleService saleService, Gson gson) {
        this.carService = carService;
        this.customerService = customerService;
        this.partService = partService;
        this.supplierService = supplierService;
        this.saleService = saleService;
        this.gson = gson;;
    }

    @Override
    public void run(String... args) throws Exception {
        importData();
        orderedCustomers();
        carsFromMakeToyota();
        localSuppliers();
        carsWithTheirListOfParts();
        totalSalesByCustomer();
        salesWithAppliedDiscount();
    }

    private void salesWithAppliedDiscount() throws IOException {
        List<SaleWithDiscountInfoDto> allSalesWithDiscountInfo = saleService.getAllSalesWithDiscountInfo();

        String json = gson.toJson(allSalesWithDiscountInfo);

        Files.writeString(Path.of(JSON_EXPORT_FOLDER_PATH + "sales-discounts.json"), json);
    }

    private void totalSalesByCustomer() throws IOException {
        List<CustomerWithTotalSalesDto> customers = customerService.getAllCustomersWithAtLeastOneCarSale();

        String json = gson.toJson(customers);

        Files.writeString(Path.of(JSON_EXPORT_FOLDER_PATH + "customers-total-sales.json"), json);
    }

    private void carsWithTheirListOfParts() throws IOException {
        List<CarWrapper> carDtos = carService.getAllCarsWithParts();

        String json = gson.toJson(carDtos);

        Files.writeString(Path.of(JSON_EXPORT_FOLDER_PATH + "cars-and-parts.json"), json);
    }

    private void localSuppliers() throws IOException {
        List<SupplierWithIdNameAndPartsCountDto> supplierDtos = supplierService.getAllLocalSuppliers();

        String json = gson.toJson(supplierDtos);

        Files.writeString(Path.of(JSON_EXPORT_FOLDER_PATH + "local-suppliers.json"), json);
    }

    private void carsFromMakeToyota() throws IOException {
        List<CarWithIdMakeModelTravelDistanceDto> toyotaCars = carService.getAllToyotaCars();

        String json = gson.toJson(toyotaCars);

        Files.writeString(Path.of(JSON_EXPORT_FOLDER_PATH + "toyota-cars.json"), json);
    }

    private void orderedCustomers() throws IOException {
        List<CustomerExportDto> dtos = customerService.getAllCustomersSortedByBirthDate();

        String json = gson.toJson(dtos);

        Files.writeString(Path.of(JSON_EXPORT_FOLDER_PATH + "ordered-customers.json"), json);
    }

    private void importData() throws IOException {
        importSuppliers();
        importParts();
        importCars();
        importCustomers();
        importSales();
    }

    private void importSales() {
        saleService.generateSales();
    }

    private void importCustomers() throws IOException {
        String customersJson = Files.readString(Path.of(JSON_IMPORT_FOLDER_PATH + CUSTOMERS_JSON_NAME));
        CustomerImportDto[] dtos = gson.fromJson(customersJson, CustomerImportDto[].class);

        customerService.addAll(dtos);
    }

    private void importParts() throws IOException {
        String partsJson = Files.readString(Path.of(JSON_IMPORT_FOLDER_PATH + PARTS_JSON_NAME));
        PartImportDto[] dtos = gson.fromJson(partsJson, PartImportDto[].class);

        partService.addAll(dtos);
    }

    private void importSuppliers() throws IOException {
        String suppliersJson = Files.readString(Path.of(JSON_IMPORT_FOLDER_PATH + SUPPLIERS_JSON_NAME));
        SupplierImportDto[] dtos = gson.fromJson(suppliersJson, SupplierImportDto[].class);

        supplierService.addAll(dtos);
    }

    private void importCars() throws IOException {
        String carsJson = Files.readString(Path.of(JSON_IMPORT_FOLDER_PATH + CARS_JSON_NAME));

        CarImportDto[] dtos = gson.fromJson(carsJson, CarImportDto[].class);

        carService.addAll(dtos);
    }
}
