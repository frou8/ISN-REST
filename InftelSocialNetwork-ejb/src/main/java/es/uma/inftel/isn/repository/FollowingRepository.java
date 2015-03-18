/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.repository;

import es.uma.inftel.isn.entity.Following;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JOSE
 */
@Component
@Repository
public interface FollowingRepository extends MongoRepository<Following, String> {

    @Query("{ 'user.email' : ?0 }")
    Following findByUserEmail(String email);

    @Query("{ 'following.email' : ?0}")
    List<Following> findByFollowingUserEmail(String email);

    @Query("{'user.email' : ?0 , 'following.email' : ?1}")
    Following findByFollowingUserEmail(String emailFollower, String emailFollowing);

}
