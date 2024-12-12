package bg.softuni.carDealer.dots;

import com.google.gson.annotations.Expose;

public class SupplierImportDto {

    @Expose
    private String name;
    @Expose
    private Boolean isImporter;

    public SupplierImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }
}
