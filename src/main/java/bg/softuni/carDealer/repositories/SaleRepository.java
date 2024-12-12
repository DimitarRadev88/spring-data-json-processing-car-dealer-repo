package bg.softuni.carDealer.repositories;

import bg.softuni.carDealer.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("""
            SELECT SUM(p.price) FROM Sale s
            JOIN s.customer c
            JOIN s.car car
            JOIN car.parts p
            WHERE c.id = ?1
            GROUP BY c
            """)
    BigDecimal getSpentMoneyByCustomerId(Long id);

}
