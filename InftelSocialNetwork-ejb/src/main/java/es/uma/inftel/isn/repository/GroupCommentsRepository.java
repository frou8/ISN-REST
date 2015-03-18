/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.repository;

import es.uma.inftel.isn.entity.GroupComments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface GroupCommentsRepository extends MongoRepository<GroupComments, String> {

    @Query("{'_id': ?0 }")
    public GroupComments findCommentsByGroupId(String id);
    
    @Query("{'group.admin': ?0,'group.name': ?1}")
    public GroupComments findCommentsByGroupAdmin(String admin,String name);
    
}
