package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.controller;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "6") int size,
                       @RequestParam(defaultValue = "id") String sortBy,
                       @RequestParam(defaultValue = "asc") String sortDir,
                       Model model) {

        Page<Dish> dishPage = dishService.searchDishes(name, category, page, size, sortBy, sortDir);

        model.addAttribute("dishes", dishPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", dishPage.getTotalPages());
        model.addAttribute("totalItems", dishPage.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("name", name);
        model.addAttribute("category", category);

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