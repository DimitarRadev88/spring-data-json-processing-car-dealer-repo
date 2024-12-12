package bg.softuni.carDealer.repositories;

import bg.softuni.carDealer.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

}
