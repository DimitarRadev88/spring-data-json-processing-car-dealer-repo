package bg.softuni.carDealer.repositories;

import bg.softuni.carDealer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("""
            FROM Customer c
            JOIN FETCH c.sales sales
            ORDER BY c.birthDate
            """)
    List<Customer> findAllOrderByBirthDateAndYoungDriver();

    @Query("""
            FROM Customer c
            JOIN FETCH c.sales
            WHERE size(c.sales) > 0
            """)
    List<Customer> findAllSaleNotEmpty();
}
