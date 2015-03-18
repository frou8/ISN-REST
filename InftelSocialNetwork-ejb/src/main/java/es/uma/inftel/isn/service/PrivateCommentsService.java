package es.uma.inftel.isn.service;

import es.uma.inftel.isn.util.Comment;
import es.uma.inftel.isn.entity.PrivateComments;
import es.uma.inftel.isn.repository.PrivateCommentsRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class PrivateCommentsService extends AbstractService<PrivateComments, PrivateCommentsRepository> {
    
    @Autowired
    private PrivateCommentsRepository repository;

    @Override
    public PrivateCommentsRepository getRepository() {
        return repository;
    }
    
    public PrivateComments findCommentsByUserEmail(String email){
        return repository.findPrivateCommentsByEmail(email);
    }
    
    public void insertComment(String userEmail, Comment newComment){
        if(repository.findPrivateCommentsByEmail(userEmail)!=null){
            PrivateComments privateComments = repository.findPrivateCommentsByEmail(userEmail);
            privateComments.getCommentsList().add(newComment);
            insert(privateComments);
        } else {
            PrivateComments privateComments = new PrivateComments();
            ArrayList<Comment> commentsList = new ArrayList<>();
            commentsList.add(newComment);
            privateComments.setCommentsList(commentsList);
            privateComments.setUserEmail(userEmail);
            insert(privateComments);
        }
    }
    
    public void deletePrivateComments(String userEmail, Comment comment) {
        PrivateComments privateComments = repository.findPrivateCommentsByEmail(userEmail);
        privateComments.getCommentsList().remove(comment);
        insert(privateComments);
    }
}