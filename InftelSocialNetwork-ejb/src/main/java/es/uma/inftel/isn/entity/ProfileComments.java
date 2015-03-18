package es.uma.inftel.isn.entity;

import es.uma.inftel.isn.util.Comment;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profileComments")
public class ProfileComments implements Serializable {

    @Id
    private String id;
    private String userEmail;
    private ArrayList<Comment> commentsList;

    public ProfileComments() {
        this.commentsList = new ArrayList<>();
    }

    public ProfileComments(String id, String userEmail, ArrayList<Comment> commentsList) {
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
