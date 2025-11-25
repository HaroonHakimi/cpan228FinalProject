package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.service;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.SecondaryDish;
import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository.SecondaryDishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecondaryDishService {

    @Autowired
    private SecondaryDishRepository repository;

    public List<SecondaryDish> getAllDishes() {
        return repository.findAll();
    }

    public Optional<SecondaryDish> getDishById(Long id) {
        return repository.findById(id);
    }

    public SecondaryDish saveDish(SecondaryDish dish) {
        dish.setSourceService("Primary Service");
        return repository.save(dish);
    }
}