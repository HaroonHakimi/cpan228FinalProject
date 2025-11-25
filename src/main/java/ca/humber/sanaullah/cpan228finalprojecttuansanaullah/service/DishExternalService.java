package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DishExternalService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DishService dishService;

    // Call external API and save to local database
    public Dish fetchAndSaveDish(Long id) {
        String url = "https://jsonplaceholder.typicode.com/posts/" + id;

        // Fetch from external API (simulating external dish data)
        ExternalPost post = restTemplate.getForObject(url, ExternalPost.class);

        if (post != null) {
            // Convert external data to Dish
            Dish dish = new Dish();
            dish.setName(post.getTitle());
            dish.setCategory("External");
            dish.setPrice(9.99 + (id % 10)); // Simulated price

            // Save to local database
            dishService.saveDish(dish);

            return dish;
        }

        return null;
    }

    // Inner class to represent external API response
    public static class ExternalPost {
        private Long id;
        private String title;
        private String body;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}