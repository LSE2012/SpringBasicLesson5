package edu.homework.lesson4.repository;

import edu.homework.lesson4.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findUsersById(Integer id);

    List<Users> findUsersByNameIs(String name);

    List<Users> findUsersByEmailIs(String email);

    List<Users> getByEmailContains(String email);

    List<Users> searchByNameContains(String name);

    @Query(value = "SELECT U.* FROM USERS U", nativeQuery = true)
    List<Users>  selectAllUsers();

    @Query(value = "SELECT U.* FROM USERS U WHERE U.ID = ?1", nativeQuery = true)
    Users selectUserById(int userid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE Users SET email = ?2 WHERE id = ?1", nativeQuery = true)
    void updateUserByID(int userId, String newEmail);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Users U where U.id = ?1", nativeQuery = true)
    void deleteUserRecordByID(int id);

}
