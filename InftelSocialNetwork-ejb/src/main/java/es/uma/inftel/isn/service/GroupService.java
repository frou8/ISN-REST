/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uma.inftel.isn.service;

import es.uma.inftel.isn.entity.Group;
import es.uma.inftel.isn.entity.GroupComments;
import es.uma.inftel.isn.entity.User;
import es.uma.inftel.isn.repository.GroupRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author loubna
 */
@Component
@Service
public class GroupService extends AbstractService<Group, GroupRepository> {

    @Autowired
    private GroupRepository repository;

    @Autowired
    private GroupCommentsService groupCommentsService;

    @Override
    public GroupRepository getRepository() {
        return repository;
    }

    public void insertUser(User user, String admin, String namegroup) {
        Boolean flag = false;
        Group group = repository.groupUserFind(namegroup, admin);
        for (User currentUser : group.getUser()) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                flag = true;
            }
        }
        if (flag == false) {
            group.getUser().add(user);
            insert(group);
        }
    }

    public void deleteGroup(String admin, String namegroup) {
        Group group = repository.groupUserFind(namegroup, admin);
        if (group != null) {
            repository.delete(group);
            GroupComments comments = groupCommentsService.getRepository().findCommentsByGroupAdmin(admin, namegroup);
            if (comments != null) {
                groupCommentsService.delete(comments);
            }
        }
    }

    public void deleteUser(User user, String admin, String namegroup) {
        Group group = repository.groupUserFind(namegroup, admin);

        if (group != null) {
            
            group.getUser().remove(user);

            if (group.getUser().size() <= 0) {
                repository.delete(group);
                GroupComments comments = groupCommentsService.getRepository().findCommentsByGroupAdmin(admin, namegroup);
                if (comments != null) {
                    groupCommentsService.delete(comments);
                }
            } else {
                insert(group);
            }
        }
    }

    public void deleteMember(Group group, User user) {
        group.getUser().remove(user);
        insert(group);

    }

    public List<Group> emailGroupFind(String email) {
        return repository.emailGroupFind(email);
    }

    public List<Group> nameGroupFind(String name, String email) {
        return repository.nameGroupFind(name, email);
    }

    public List<User> listNameGroupFind(String name) {
        return repository.listNameGroupFind(name);
    }

    public Group listIdGroupFind(String id) {
        return repository.listIdGroupFind(id);
    }

    // devuelve un grupo por su id
    public Group findGroup(String id) {
        return repository.findOne(id);
    }

    public Group listNameGroupFind(String name, String admin) {
        return repository.groupUserFind(name, admin);
    }

    public List<User> listFilterNameGroupFind(String id, String email) {
        return repository.listFilterNameGroupFind(id, email);
    }

    public Group findOneGroup(String id){
        return repository.findOne(id);
    }
}
