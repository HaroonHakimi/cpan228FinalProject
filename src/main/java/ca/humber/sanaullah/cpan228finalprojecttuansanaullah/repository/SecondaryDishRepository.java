package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.repository;

import ca.humber.sanaullah.cpan228finalprojecttuansanaullah.model.SecondaryDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondaryDishRepository extends JpaRepository<SecondaryDish, Long> {
}