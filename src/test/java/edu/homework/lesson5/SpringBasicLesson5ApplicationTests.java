package edu.homework.lesson5;

import edu.homework.lesson5.entity.Users;
import edu.homework.lesson5.repository.UsersCrudRepository;
import edu.homework.lesson5.repository.UsersRepository;
import edu.homework.lesson5.services.UsersService;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

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
    public void testUsersById() {
        System.out.println("====== testUsersById ========");
        var userID = 2;
        var users = usersRepository.findUsersById(userID);
        users.forEach(System.out::println);
        Assert.assertTrue(users.get(0).getId().equals(userID));
    }

    @Test
    public void testUserByName() {
        System.out.println("====== testUserByName ========");
        var userName = "Tester1";
        var users = usersRepository.findUsersByNameIs(userName);
        users.forEach(System.out::println);
        Assert.assertTrue(users.get(0).getName().contains(userName));
    }

    @Test
    void testGetUserByEmail() {
        System.out.println("====== testGetUserByEmail ========");
        var userEmail = "tester1.tester@gmail.com";
        var users = usersRepository.findUsersByEmailIs(userEmail);
        users.forEach(System.out::println);
        Assert.assertTrue(users.get(0).getEmail().equals(userEmail));
    }

    @Test
    void testUsersServicesFindAll() {
        System.out.println("====== testUsersServicesFindAl ========");
        var countUsersRepository = usersRepository.selectAllUsers().size();
        List<Users> usersList;
        usersList = new UsersService(usersRepository).findAll();
        usersList.forEach(System.out::println);
        Assert.assertEquals(usersList.size(),countUsersRepository);
    }

    @Test
    void testUserServicesSave() {
        Users user = new Users();
        String newUserName = "TesterName_" + RandomString.make(5);
        String newUserEmail = RandomString.make(8) + "@ukr.ua";
        user.setName(newUserName);
        user.setEmail(newUserEmail);
        System.out.println("create new User= " + user.toString());
        usersService.save(user);
        var savedUser = usersRepository.getByEmailContains(newUserEmail);
        savedUser.forEach(System.out::println);
        Assert.assertEquals(savedUser.get(0).getName(),newUserName);
        Assert.assertEquals(savedUser.get(0).getEmail(),newUserEmail);
    }

    @Test
    void testUsersRepository() {
        String partEmail = "@gmail";
        List<Users> userList;
        userList = usersRepository.getByEmailContains(partEmail);
        userList.forEach((c) -> System.out.println(c));
        org.assertj.core.api.Assertions.assertThat(userList).filteredOn(c  -> c.getEmail().contains(partEmail)).isNotEmpty() ;
    }

    @Test
    public void testUsersRepositoryGetEmail() {
        String userName = "Tester";
        List<Users> userList;
        userList = usersRepository.searchByNameContains(userName);
        userList.forEach(System.out::println);
        org.assertj.core.api.Assertions.assertThat(userList).filteredOn(c-> c.getName().contains(userName)).isNotEmpty();
    }

    @Test
    public void testUsersRepositoryQuerySelect() {
        List<Users> usersListService = usersService.findAll();
        List<Users> userListTest = usersRepository.selectAllUsers();
        userListTest.forEach(System.out::println);
        Assert.assertEquals(userListTest, usersListService);
    }

    @Test
    public void testUsersRepositoryQuerySelectParam() {
        Integer userID = 1 ;
        Users users = usersRepository.selectUserById(userID);
        System.out.println("users1 == " + users.toString());
        Assert.assertTrue(users.getId().equals(userID));
    }

    @Test
    public void testUsersRepositoryQueryUpdateById() {
        int userID = usersCrudRepository.getMaxUserID();
        Users userBeforeUpdate = usersRepository.selectUserById(userID);
        System.out.println("before USER (id= " + userID + ")= " + userBeforeUpdate.toString());
        usersRepository.updateUserByID(userID, RandomString.make(8) + "@ukr.ua");
        Users userAfterUpdate = usersRepository.selectUserById(userID);
        System.out.println("after USER (id= " + userID + ")= " + userAfterUpdate.toString());
        Assert.assertNotEquals(userBeforeUpdate,userAfterUpdate);
    }

    @Test()
    // Task4
    public void testUsersRepositorySaveException() {
        Users users = new Users();
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
            usersRepository.save(users);;
        });
    }
}
