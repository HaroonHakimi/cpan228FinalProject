package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DishMicroserviceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservice.secondary.url:http://localhost:8081}")
    private String secondaryServiceUrl;

    // Send dish data to secondary microservice
    public Dish sendToSecondaryService(Dish dish) {
        String url = secondaryServiceUrl + "/api/secondary/dishes";

        try {
            Dish response = restTemplate.postForObject(url, dish, Dish.class);
            return response;
        } catch (Exception e) {
            System.err.println("Failed to send to secondary service: " + e.getMessage());
            return null;
        }
    }

    // Fetch dish from secondary microservice
    public Dish fetchFromSecondaryService(Long id) {
        String url = secondaryServiceUrl + "/api/secondary/dishes/" + id;

        try {
            Dish dish = restTemplate.getForObject(url, Dish.class);
            return dish;
        } catch (Exception e) {
            System.err.println("Failed to fetch from secondary service: " + e.getMessage());
            return null;
        }
    }
}