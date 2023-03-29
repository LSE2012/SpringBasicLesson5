package edu.homework.lesson5;

import ch.qos.logback.core.testUtil.RandomUtil;
import edu.homework.lesson5.entity.Cards;
import edu.homework.lesson5.repository.CardsRepository;
import edu.homework.lesson5.services.CardsService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SpringBasicCardsRepositoryTests {
    @Autowired
    private CardsRepository cardsRepository;


    @Autowired
    private CardsService cardsService;

    @Test
    public void testCardsRepositoryGetCardId() {
        var listCardEtalon = cardsService.findAll();
        var listCardsTest = cardsRepository.findAll();
        listCardsTest.forEach(System.out::println);
        Assert.assertEquals(listCardEtalon, listCardsTest);
    }

    @Test
    public void testCardsFindAll() {
        var cardID = 1;
        List<Cards> cardsList = cardsRepository.findAllByCardId(cardID);
        cardsList.forEach(System.out::println);
        Assert.assertEquals(cardID, cardsList.get(0).getCardId());
    }

    @Test
    public void testCardSaveRepository() {
        List<Cards> beforeCardsList = cardsService.findAll();
        Cards card = new Cards();
        card.setNumber("123654");
        card.setCurrencycode(980);
        card.setUserid(2);
        cardsRepository.save(card);
        List<Cards> afterCardsList = cardsService.findAll();
        Assert.assertNotEquals(beforeCardsList, afterCardsList);
    }

    @Test
    public void testCardsUpdateRepository() {
        List<Cards> beforeCardsList = cardsService.findAll();
        var count = cardsRepository.count();
        cardsRepository.deleteById(Math.toIntExact(count));
        List<Cards> afterCardsList = cardsService.findAll();
        Assert.assertNotEquals(beforeCardsList, afterCardsList);
    }


    @Test
    public void testCardServicesFindAll() {
        List<Cards> beforeCardsList = cardsRepository.findAll();
        cardsService = new CardsService(cardsRepository);
        List<Cards> afterCardsList = cardsService.findAll();
        afterCardsList.forEach(System.out::println);
        Assert.assertEquals(beforeCardsList, afterCardsList);
    }

    @Test
    public void testCardServicefindOne() {
        var cardID = 2;
        cardsService = new CardsService(cardsRepository);
        Cards card = cardsService.findOne(cardID);
        System.out.println("card = " + card.toString());
        Assert.assertEquals(cardID, card.getCardId());
    }

    @Test
    public void testCardServicesSave() {
        List<Cards> beforeCardsList = cardsRepository.findAll();
        Cards cards = new Cards();
        cards.setNumber("789654123");
        cards.setCurrencycode(840);
        cards.setUserid(3);
        cardsService.save(cards);
        cardsService.findAll().forEach(System.out::println);
        List<Cards> afterCardsList = cardsRepository.findAll();
        Assert.assertNotEquals(beforeCardsList, afterCardsList);
    }


    @Test
    public void testCardServiceUpdate() {
        var cardId = 2;
        Cards beforeUpdateCard = cardsService.findOne(cardId);
        var cards = new Cards();
        cards.setNumber("123409" + RandomUtil.getPositiveInt());
        cards.setCurrencycode(840);
        cards.setUserid(cardId);
        cardsService.update(2, cards);
        Cards afterUpdateCard = cardsService.findOne(cardId);
        System.out.println("card = " + afterUpdateCard.toString());
        Assert.assertNotEquals(beforeUpdateCard, afterUpdateCard);
    }

    @Test
    public void testCardServicesDelete() {
        var cardNumber = "123456789";
        Cards newTestCard = new Cards();
        newTestCard.setNumber(cardNumber);
        newTestCard.setCurrencycode(980);
        newTestCard.setUserid(5);
        cardsService.save(newTestCard);

        var count = cardsRepository.getDistinctFirstByNumberIs(cardNumber).getCardId();
        List<Cards> beforeCard = cardsRepository.findAllByCardId(Math.toIntExact(count));
        System.out.println("beforeCard = " + beforeCard.toString());
        cardsService.delete(Math.toIntExact(count));
        List<Cards> afterCard = cardsRepository.findAllByCardId(Math.toIntExact(count));
        System.out.println(" afterCard = " +  afterCard.toString());
        Assert.assertNotEquals(beforeCard, afterCard);
    }

    @Test
    public void testCardsRepositoryFindAllByNumber() {
        var cardID = 2;
        cardsService = new CardsService(cardsRepository);
        Cards beforeCard = cardsService.findOne(cardID);
        System.out.println("beforeCardNumber = " + beforeCard.getNumber());
        var afterCards = cardsRepository.findAllByNumberIs(beforeCard.getNumber()).get(0);
        System.out.println("afterCardNumber  = " + afterCards.getNumber());
        Assert.assertEquals(beforeCard, afterCards);
    }

}
