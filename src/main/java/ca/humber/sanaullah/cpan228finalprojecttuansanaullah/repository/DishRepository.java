package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    // Custom query methods for search/filter
    List<Dish> findByNameContainingIgnoreCase(String name);
    List<Dish> findByCategory(String category);
    List<Dish> findByNameContainingIgnoreCaseAndCategory(String name, String category);
}