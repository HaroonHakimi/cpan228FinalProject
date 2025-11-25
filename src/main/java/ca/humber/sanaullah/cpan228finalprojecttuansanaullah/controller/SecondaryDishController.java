package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.controller;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.SecondaryDish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service.SecondaryDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/secondary/dishes")
public class SecondaryDishController {

    @Autowired
    private SecondaryDishService service;

    @GetMapping
    public ResponseEntity<List<SecondaryDish>> getAllDishes() {
        return ResponseEntity.ok(service.getAllDishes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecondaryDish> getDishById(@PathVariable Long id) {
        Optional<SecondaryDish> dish = service.getDishById(id);
        return dish.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SecondaryDish> createDish(@RequestBody Dish dish) {
        // Convert Dish to SecondaryDish
        SecondaryDish secondaryDish = new SecondaryDish();
        secondaryDish.setName(dish.getName());
        secondaryDish.setCategory(dish.getCategory());
        secondaryDish.setPrice(dish.getPrice());

        SecondaryDish saved = service.saveDish(secondaryDish);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}