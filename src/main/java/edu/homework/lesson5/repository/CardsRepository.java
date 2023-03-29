package edu.homework.lesson5.repository;

import edu.homework.lesson5.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards,Integer> {
    List<Cards> findAllByCardId(int cardId);
    List<Cards> findAllByNumberIs(String number);
    Cards getDistinctFirstByNumberIs(String number);
}
