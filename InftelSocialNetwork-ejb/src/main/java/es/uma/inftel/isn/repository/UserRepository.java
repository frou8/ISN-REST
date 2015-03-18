/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.repository;

import es.uma.inftel.isn.entity.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alfredo
 */
@Component
@Repository
public interface UserRepository extends MongoRepository<User, String>{
    @Query("{ 'email' : ?0 }")
    List<User> findUsersByEmail(String email);
    
    @Query("{'name': {'$regex': '.*?0.*', '$options': 'i'}}")
    List<User> findUsersByName(String searchData);
    
    @Query("{ 'email' : ?0 }")
    User findUserByEmail(String email);
}
