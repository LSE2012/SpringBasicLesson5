package edu.homework.lesson4.repository;

import edu.homework.lesson4.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersCrudRepository extends CrudRepository<Users,Integer> {

    @Query(value = "SELECT MAX(id) as id FROM Users U", nativeQuery = true )
    int getMaxUserID() ;

}
