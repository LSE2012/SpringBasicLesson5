package edu.homework.lesson5;

import edu.homework.lesson5.repository.UsersRepository;
import edu.homework.lesson5.services.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureDataJpa
@SpringBootTest
public class UnitTests {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;
    @Before
    public void initUserServices() {
        usersService = new UsersService(usersRepository);
    }

    @Test
    public void testException() {

        var userID = 2;
        var users = usersRepository.findUsersById(userID);
        System.out.println("====== testUsersById ========");
        users.forEach(System.out::println);
    }

}
