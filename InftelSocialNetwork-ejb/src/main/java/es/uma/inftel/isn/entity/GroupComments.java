package es.uma.inftel.isn.entity;

import es.uma.inftel.isn.util.Comment;
import java.io.Serializable;
import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "groupComments")
public class GroupComments implements Serializable {

    @Id
    private String id;
    private Group group;
    private ArrayList<Comment> commentsList;

    public GroupComments() {
        this.commentsList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public ArrayList<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(ArrayList<Comment> commentsList) {
        this.commentsList = commentsList;
    }

}
