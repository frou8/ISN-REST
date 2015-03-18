package es.uma.inftel.isn.service;

import es.uma.inftel.isn.entity.Group;
import es.uma.inftel.isn.entity.GroupComments;
import es.uma.inftel.isn.repository.GroupCommentsRepository;
import es.uma.inftel.isn.util.Comment;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class GroupCommentsService extends AbstractService<GroupComments, GroupCommentsRepository> {
    
    @Autowired
    private GroupCommentsRepository repository;

    @Override
    public GroupCommentsRepository getRepository() {
        return repository;
    }
    public GroupComments findCommentsByGroup(String id){
         return repository.findCommentsByGroupId(id);
    }
    public GroupComments findCommentsByGroupAdmin(String admin,String name){
        return repository.findCommentsByGroupAdmin(admin, name);
    }

     
    public void insertComment(Group group, GroupComments groupComments, Comment newComment){
        if(groupComments != null){
            groupComments.getCommentsList().add(newComment);
            insert(groupComments);
        } else {
            groupComments = new GroupComments();
            groupComments.setGroup(group);
            ArrayList<Comment> commentsList = new ArrayList<>();
            commentsList.add(newComment);
            groupComments.setCommentsList(commentsList);
            insert(groupComments);
        }
    }
    
    public void deleteGroupComment(GroupComments groupComments, Comment comment) {
        if (groupComments == null) {
            return;
        }
        groupComments.getCommentsList().remove(comment);
        insert(groupComments);
    }
}