package edu.homework.lesson5.repository;

import edu.homework.lesson5.entity.Cards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsCrudRepository extends CrudRepository<Cards, Integer> {
    @Override
    Optional<Cards> findById(Integer integer);
}