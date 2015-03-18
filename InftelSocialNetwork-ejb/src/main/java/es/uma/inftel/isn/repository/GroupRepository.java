/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.repository;

import es.uma.inftel.isn.entity.Group;
import es.uma.inftel.isn.entity.User;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author loubna
 */
@Component
@Repository
public interface GroupRepository extends MongoRepository<Group, String> {

    @Query("{'name' : ?0, 'admin' : ?1}")
    Group groupUserFind(String name, String admin);

    @Query("{ '_id' : ?0}")
    Group listIdGroupFind(String id);
    
    @Query("{ '_id' : ?0, 'userList.email' : ?1}")
    List<User> listFilterNameGroupFind(String id, String email);

    @Query("{ 'name' : ?0}")
    List<User> listNameGroupFind(String name);

    @Query("{ 'userList.email' : ?0}")
    List<Group> emailGroupFind(String email);

    // filtro por nombre de grupo de un usuario
    @Query("{ 'name' : {'$regex': '.*?0.*', '$options': 'i'}, 'userList.email' : ?1}")
    List<Group> nameGroupFind(String name, String email);

}
