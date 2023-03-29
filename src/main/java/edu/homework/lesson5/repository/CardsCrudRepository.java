package edu.homework.lesson5.repository;

import edu.homework.lesson5.entity.Cards;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsCrudRepository extends CrudRepository<Cards, Integer> {
    @Override
    Optional<Cards> findById(Integer integer);

    @Query(value = "SELECT 1" , nativeQuery = true)
    String testConnectionCardsCrudRepository();
}
