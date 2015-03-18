/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.repository;

import es.uma.inftel.isn.entity.ProfileComments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ProfileCommentsRepository extends MongoRepository<ProfileComments, String> {

    @Query("{'userEmail': ?0 }")
    public ProfileComments findCommentsByUserEmail(String email);
    
}
