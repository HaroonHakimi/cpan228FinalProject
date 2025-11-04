package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Search/Filter methods
    public List<Dish> searchDishes(String name, String category) {
        if (name != null && !name.isEmpty() && category != null && !category.isEmpty() && !category.equals("All")) {
            return dishRepository.findByNameContainingIgnoreCaseAndCategory(name, category);
        } else if (name != null && !name.isEmpty()) {
            return dishRepository.findByNameContainingIgnoreCase(name);
        } else if (category != null && !category.isEmpty() && !category.equals("All")) {
            return dishRepository.findByCategory(category);
        } else {
            return dishRepository.findAll();
        }
    }
}