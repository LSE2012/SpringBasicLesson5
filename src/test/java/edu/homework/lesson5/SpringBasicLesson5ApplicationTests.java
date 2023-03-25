package edu.homework.lesson5;

import edu.homework.lesson5.entity.Users;
import edu.homework.lesson5.repository.UsersCrudRepository;
import edu.homework.lesson5.repository.UsersRepository;
import edu.homework.lesson5.services.UsersService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBasicLesson5ApplicationTests {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersCrudRepository usersCrudRepository;

    @Autowired
    private UsersService usersService;

    @Test
    void testUsersById() {
        var userID = 2;
        var users = usersRepository.findUsersById(userID);
        System.out.println("====== testUsersById ========");
        users.forEach(System.out::println);

    }

    @Test
    void testUserByName() {
        var userName = "Tester1";
        var users = usersRepository.findUsersByNameIs(userName);
        System.out.println("====== testUserByName ========");
        users.forEach(System.out::println);
    }

    @Test
    void testGetUserByEmail() {
        var userEmail = "tester1.tester@gmail.com";
        var users = usersRepository.findUsersByEmailIs(userEmail);
        System.out.println("====== testGetUserByEmail ========");
        users.forEach(System.out::println);
    }

    @Test
    void testUsersServicesFindAll() {
        List<Users> usersList;
        usersList = new UsersService(usersRepository).findAll();
        System.out.println("====== testUsersServicesFindAl ========");
        usersList.forEach(System.out::println);
    }

    @Test
    void testUSerServicesSave() {
        Users user = new Users();
        user.setName("TesterName_" + RandomString.make(5));
        user.setEmail(RandomString.make(8) + "@ukr.ua");
        usersService.save(user);
    }

    @Test
    void testUsersRepository() {
        List<Users> userList;
        userList = usersRepository.getByEmailContains("@gmail");
        userList.forEach(System.out::println);
    }

    @Test
    public void testUsersRepositoryGetEmail() {
        List<Users> userList;
        userList = usersRepository.searchByNameContains("Tester");
        userList.forEach(System.out::println);
    }

    @Test
    public void testUsersRepositoryQuerySelect() {
        List<Users> userList = usersRepository.selectAllUsers();
        userList.forEach(System.out::println);
    }

    @Test
    public void testUsersRepositoryQuerySelectParam() {
        Users users = usersRepository.selectUserById(1);
        System.out.println("users1 == " + users.toString());
    }

    @Test
    public void testUsersRepositoryQueryUpdateById() {
        int userID = usersCrudRepository.getMaxUserID();
        Users user1 = usersRepository.selectUserById(userID);
        System.out.println("before USER (id= " + userID + ")= " + user1.toString());
        usersRepository.updateUserByID(userID, RandomString.make(8) + "@ukr.ua");
        Users user2 = usersRepository.selectUserById(userID);
        System.out.println("after USER (id= " + userID + ")= " + user2.toString());
    }

    @Test
    public void testUsersRepositoryQueryDeleteByID() {
        int maxUserID = usersCrudRepository.getMaxUserID();
        System.out.println("Before Max userID= " + maxUserID);
        usersRepository.deleteUserRecordByID(maxUserID);
        System.out.println("After Max userID= " + usersCrudRepository.getMaxUserID());

    }
}
