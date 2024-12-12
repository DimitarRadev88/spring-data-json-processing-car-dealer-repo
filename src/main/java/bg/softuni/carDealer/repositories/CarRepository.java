package bg.softuni.carDealer.repositories;

import bg.softuni.carDealer.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByMakeEqualsOrderByModelAscTravelledDistanceDesc(String make);

    @Query("""
            FROM Car c
            JOIN FETCH c.parts
            """)
    List<Car> findAllWithParts();

    @Query("""
            SELECT SUM(p.price) FROM Car c
            JOIN c.parts p
            WHERE c.id = ?1
            """)
    BigDecimal findPriceById(long id);

}
