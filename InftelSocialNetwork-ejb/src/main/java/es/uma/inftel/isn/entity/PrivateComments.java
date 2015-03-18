package es.uma.inftel.isn.entity;

import es.uma.inftel.isn.util.Comment;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "privateComments")
public class PrivateComments implements Serializable{
    
    @Id
    private String id;
    private String userEmail;
    private ArrayList<Comment> commentsList;

    public PrivateComments() {
        this.commentsList = new ArrayList<>();
    }

    public PrivateComments(String id, String userEmail, ArrayList<Comment> commentsList) {
        this.id = id;
        this.userEmail = userEmail;
        this.commentsList = commentsList;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
