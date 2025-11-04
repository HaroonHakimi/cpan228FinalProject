package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    // Get all dishes
    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }

    // Get dishes with pagination and sorting
    public Page<Dish> getDishes(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return dishRepository.findAll(pageable);
    }

    // Save new dish
    public void saveDish(Dish dish) {
        dishRepository.save(dish);
    }

    // Get dish by ID
    public Optional<Dish> getDishById(Long id) {
        return dishRepository.findById(id);
    }

    // Update dish
    public void updateDish(Dish dish) {
        dishRepository.save(dish);
    }

    // Delete dish
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    // Search/Filter with pagination
    public Page<Dish> searchDishes(String name, String category, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (name != null && !name.isEmpty() && category != null && !category.isEmpty() && !category.equals("All")) {
            return dishRepository.findByNameContainingIgnoreCaseAndCategory(name, category, pageable);
        } else if (name != null && !name.isEmpty()) {
            return dishRepository.findByNameContainingIgnoreCase(name, pageable);
        } else if (category != null && !category.isEmpty() && !category.equals("All")) {
            return dishRepository.findByCategory(category, pageable);
        } else {
            return dishRepository.findAll(pageable);
        }
    }
}