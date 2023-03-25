package edu.homework.lesson4.services;

import edu.homework.lesson4.entity.Cards;
import edu.homework.lesson4.repository.CardsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional (readOnly = true)
public class CardsService {
    private final CardsRepository cardsRepository;

    public CardsService(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    public List<Cards> findAll() {
        return cardsRepository.findAll();
    }

    public Cards findOne(int cardId) {
        Optional<Cards> foundCard = cardsRepository.findById(cardId);
        return foundCard.orElse(null);
    }

    @Transactional
    public void save( Cards cards) {
        cardsRepository.save(cards);
    }

    @Transactional
    public void update(int cardId, Cards cards) {
        cards.setCardId(cardId);
        cardsRepository.save(cards);
    }

    @Transactional
    public void delete(int cardId) {
        cardsRepository.deleteById(cardId);
    }
}
