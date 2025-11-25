package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.controller;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dishes")
public class DishRestController {

    @Autowired
    private DishService dishService;

    // GET all dishes
    @GetMapping
    public ResponseEntity<List<Dish>> getAllDishes() {
        List<Dish> dishes = dishService.getDishes();
        return ResponseEntity.ok(dishes);
    }

    // GET dishes with pagination
    @GetMapping("/page")
    public ResponseEntity<Page<Dish>> getDishesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Dish> dishPage = dishService.getDishes(page, size, sortBy, sortDir);
        return ResponseEntity.ok(dishPage);
    }

    // GET dish by ID
    @GetMapping("/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable Long id) {
        Optional<Dish> dish = dishService.getDishById(id);
        return dish.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET dishes by category
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Dish>> getDishesByCategory(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Dish> dishes = dishService.searchDishes(null, category, page, size, sortBy, sortDir);
        return ResponseEntity.ok(dishes);
    }

    // POST create new dish
    @PostMapping
    public ResponseEntity<Dish> createDish(@RequestBody Dish dish) {
        dishService.saveDish(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(dish);
    }

    // PUT update dish
    @PutMapping("/{id}")
    public ResponseEntity<Dish> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        Optional<Dish> existingDish = dishService.getDishById(id);
        if (existingDish.isPresent()) {
            dish.setId(id);
            dishService.updateDish(dish);
            return ResponseEntity.ok(dish);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE dish
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        Optional<Dish> dish = dishService.getDishById(id);
        if (dish.isPresent()) {
            dishService.deleteDish(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Search dishes
    @GetMapping("/search")
    public ResponseEntity<Page<Dish>> searchDishes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Page<Dish> dishes = dishService.searchDishes(name, category, page, size, sortBy, sortDir);
        return ResponseEntity.ok(dishes);
    }
}