package bg.softuni.carDealer.repositories;

import bg.softuni.carDealer.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("""
            FROM Supplier s
            JOIN FETCH s.parts
            WHERE s.importer = FALSE
            """)
    List<Supplier> findAllByImporterFalse();

}
