package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.controller;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Value("${restaurant.name}")
    private String restaurantName;

    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("restaurantName", restaurantName);
        return "home";
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) String name,
                       @RequestParam(required = false) String category,
                       Model model) {
        List<Dish> dishes;

        if ((name != null && !name.isEmpty()) || (category != null && !category.isEmpty())) {
            dishes = dishService.searchDishes(name, category);
        } else {
            dishes = dishService.getDishes();
        }

        model.addAttribute("dishes", dishes);
        return "menu";
    }

    @GetMapping("/add-dish")
    public String showAddDishForm(Model model) {
        model.addAttribute("dish", new Dish());
        return "add-dish";
    }

    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute("dish") Dish dish) {
        dishService.saveDish(dish);
        return "redirect:/menu";
    }

    @GetMapping("/edit-dish/{id}")
    public String showEditDishForm(@PathVariable Long id, Model model) {
        Optional<Dish> dish = dishService.getDishById(id);
        if (dish.isPresent()) {
            model.addAttribute("dish", dish.get());
            return "edit-dish";
        }
        return "redirect:/menu";
    }

    @PostMapping("/edit-dish")
    public String updateDish(@ModelAttribute("dish") Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/menu";
    }

    @GetMapping("/delete-dish/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return "redirect:/menu";
    }
}