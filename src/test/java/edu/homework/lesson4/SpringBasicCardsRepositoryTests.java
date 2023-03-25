package edu.homework.lesson4;

import edu.homework.lesson4.entity.Cards;
import edu.homework.lesson4.repository.CardsCrudRepository;
import edu.homework.lesson4.repository.CardsRepository;
import edu.homework.lesson4.services.CardsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringBasicCardsRepositoryTests {
    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsCrudRepository cardsCrudRepository;

    @Autowired
    private CardsService cardsService;

    @Test
    public void testCardsRepositoryGetCardId() {
        var card = cardsRepository.findAll();
        card.forEach(System.out::println);
    }


    @Test
    public void testCardsFindAll() {
        var cardID = 1;
        List<Cards> cardsList = cardsRepository.findAllByCardId(cardID);
        cardsList.forEach(System.out::println);
    }

    @Test
    public void testCardSaveRepository() {
        Cards card = new Cards();
        card.setNumber(123654);
        card.setCurrencycode(980);
        card.setUserid(2);
        cardsRepository.save(card);
    }

    @Test
    public void testCardsUpdateRepository() {
        var count = cardsRepository.count();
        cardsRepository.deleteById(Math.toIntExact(count));
    }


    @Test
    public void testCardsCrudRepository() {
        var cardID = 2;
        var card = cardsCrudRepository.findById(cardID).orElse(null);
        System.out.println(card.toString());
    }

    @Test
    public void testCardServicesFindAll() {
        cardsService = new CardsService(cardsRepository);
        List<Cards> cardsList = cardsService.findAll();
        cardsList.forEach(System.out::println);
    }

    @Test
    public void testCardServicefindOne() {
        var cardID = 2;
        cardsService = new CardsService(cardsRepository);
        Cards card = cardsService.findOne(cardID);
        System.out.println("card = " + card.toString());
    }

    @Test
    public void testCardServicesSave() {
        Cards cards = new Cards();
        cards.setNumber(789654123);
        cards.setCurrencycode(840);
        cards.setUserid(3);
        cardsService.save(cards);
        cardsService.findAll().forEach(System.out::println);
    }


    @Test
    public void testCardServiceUpdate() {
        var cards = new Cards();
        var cardId = 2;
        cards.setNumber(123409871);
        cards.setCurrencycode(840);
        cards.setUserid(cardId);
        cardsService.update(2, cards);
        Cards card = cardsService.findOne(cardId);
        System.out.println("card = " + card.toString());
    }

    @Test
    public void testCardServicesDelete() {
        var count = cardsRepository.count();
        cardsService.delete(Math.toIntExact(count));
    }

    @Test
    public void testCardsRepositoryFindAllByNumber() {
        var cardID = 2;
        cardsService = new CardsService(cardsRepository);
        Cards card = cardsService.findOne(cardID);
        System.out.println("beforeCardNumber = " + card.getNumber());
        var cards = cardsRepository.findAllByNumberIs(card.getNumber());
        System.out.println("afterCardNumber  = " + cards.get(0).getNumber());
    }

    @Test
    public void testCardRepositoryDeleteCardByNumber() {
        int cardNumber = 77777;
        var cards = new Cards();
        Cards cards2;
        cards.setNumber(cardNumber);
        cards.setCurrencycode(978);
        cards.setUserid(15);
        cardsService.save(cards);
        cards2 = cardsRepository.getDistinctFirstByNumberIs(cardNumber);
        System.out.println("cardByNumber == " + cards2);

    }

}
