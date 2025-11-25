package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.controller;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service.DishExternalService;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service.DishMicroserviceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    @Autowired
    private DishExternalService externalService;

    @Autowired
    private DishMicroserviceClient microserviceClient;

    // Fetch from external API and save to DB
    @GetMapping("/fetch/{id}")
    public ResponseEntity<Dish> fetchAndSave(@PathVariable Long id) {
        Dish dish = externalService.fetchAndSaveDish(id);

        if (dish != null) {
            // Also send to secondary microservice
            microserviceClient.sendToSecondaryService(dish);
            return ResponseEntity.ok(dish);
        }

        return ResponseEntity.notFound().build();
    }

    // Send dish to secondary microservice
    @PostMapping("/send-to-secondary")
    public ResponseEntity<Dish> sendToSecondary(@RequestBody Dish dish) {
        Dish response = microserviceClient.sendToSecondaryService(dish);

        if (response != null) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(500).build();
    }

    // Fetch dish from secondary microservice
    @GetMapping("/fetch-from-secondary/{id}")
    public ResponseEntity<Dish> fetchFromSecondary(@PathVariable Long id) {
        Dish dish = microserviceClient.fetchFromSecondaryService(id);

        if (dish != null) {
            return ResponseEntity.ok(dish);
        }

        return ResponseEntity.notFound().build();
    }
}