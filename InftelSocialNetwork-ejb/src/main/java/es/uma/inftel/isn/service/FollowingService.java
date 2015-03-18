/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.service;

import es.uma.inftel.isn.entity.Following;
import es.uma.inftel.isn.repository.FollowingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class FollowingService extends AbstractService<Following, FollowingRepository> {

    @Autowired
    private FollowingRepository repository;

    @Override
    public FollowingRepository getRepository() {
        return repository;
    }

    public Following findByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }

    public List<Following> findUsersFollowedByUser(String email) {
        return repository.findByFollowingUserEmail(email);
    }

    public Following findByFollowerAndFollowingUserEmail(String followerEmail, String followedUserEmail) {
        return repository.findByFollowingUserEmail(followerEmail, followedUserEmail);
    }
}
