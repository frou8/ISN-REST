/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.service;

import es.uma.inftel.isn.entity.User;
import es.uma.inftel.isn.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class UserService extends AbstractService<User, UserRepository> {
    
    @Autowired
    private UserRepository repository;

    @Override
    public UserRepository getRepository() {
        return repository;
    }
    
    public List<User> findUsersByEmail(String email){
        return repository.findUsersByEmail(email);
    }
    
    public List<User> findUsersByName(String searchData){
        return repository.findUsersByName(searchData);
    }
    
    public User findUserByEmail(String email){
        return repository.findUserByEmail(email);
    }
}
