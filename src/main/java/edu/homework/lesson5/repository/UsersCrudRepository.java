package edu.homework.lesson5.repository;

import edu.homework.lesson5.entity.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersCrudRepository extends CrudRepository<Users,Integer> {

    @Query(value = "SELECT MAX(id) as id FROM Users U", nativeQuery = true )
    int getMaxUserID() ;

}
