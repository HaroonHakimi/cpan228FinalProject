package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

    // Custom query methods for search/filter with pagination
    Page<Dish> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Dish> findByCategory(String category, Pageable pageable);
    Page<Dish> findByNameContainingIgnoreCaseAndCategory(String name, String category, Pageable pageable);

    // Non-paginated versions (for backward compatibility)
    List<Dish> findByNameContainingIgnoreCase(String name);
    List<Dish> findByCategory(String category);
    List<Dish> findByNameContainingIgnoreCaseAndCategory(String name, String category);
}