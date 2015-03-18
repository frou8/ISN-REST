package es.uma.inftel.isn.service;

import es.uma.inftel.isn.entity.Following;
import es.uma.inftel.isn.entity.PrivateComments;
import es.uma.inftel.isn.entity.ProfileComments;
import es.uma.inftel.isn.entity.User;
import es.uma.inftel.isn.repository.ProfileCommentsRepository;
import es.uma.inftel.isn.util.Comment;
import es.uma.inftel.isn.util.CommentCreationDateDescComparator;
import es.uma.inftel.isn.util.WallComments;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class ProfileCommentsService extends AbstractService<ProfileComments, ProfileCommentsRepository> {

    @Autowired
    private ProfileCommentsRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private FollowingService followingService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ProfileCommentsRepository getRepository() {
        return repository;
    }

    public ProfileComments findCommentsByUserEmail(String email) {
        return repository.findCommentsByUserEmail(email);
    }

    public void insertComment(String userEmail, Comment newComment) {
        if (repository.findCommentsByUserEmail(userEmail) != null) {
            ProfileComments profileComments = repository.findCommentsByUserEmail(userEmail);
            profileComments.getCommentsList().add(newComment);
            insert(profileComments);
        } else {
            ProfileComments profileComments = new ProfileComments();
            ArrayList<Comment> commentsList = new ArrayList<>();
            commentsList.add(newComment);
            profileComments.setCommentsList(commentsList);
            profileComments.setUserEmail(userEmail);
            insert(profileComments);
        }
    }
    
    public boolean checkIfCommentExistsForUser(String userEmail, Comment comment) {
        ProfileComments profileComments = repository.findCommentsByUserEmail(userEmail);
        if (profileComments == null) {
            return false;
        }
        if (profileComments.getCommentsList() == null) {
            return false;
        }
        return profileComments.getCommentsList().contains(comment);
    }

    public WallComments findWallCommentsSortedByDateDesc(String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            return null;
        }

        List<String> usersEmails = new ArrayList<>();
        usersEmails.add(user.getEmail());

        Following following = followingService.findByUserEmail(userEmail);
        if (following != null) {
            List<User> followingUsers = following.getFollowing();
            if (followingUsers != null) {
                for (User followingUser : followingUsers) {
                    usersEmails.add(followingUser.getEmail());
                }
            }
        }

        // MongoDB equivalent:
        /*
         db.profileComments.aggregate([
         {$match: {userEmail: {$in: ['demo1@gmail.com', 'demo2@gmail.com']}}}, 
         {$unwind: "$commentsList"}, 
         {$group: {_id:null, commentsList: {$addToSet: "$commentsList"}}}, 
         {$project: {_id: 0, commentsList: "$commentsList"}}
         ]);
         */
        AggregationOperation match = Aggregation.match(Criteria.where("userEmail").in(usersEmails));
        AggregationOperation unwind = Aggregation.unwind("commentsList");
        AggregationOperation group = Aggregation.group("null").addToSet("commentsList").as("commentsList");
        AggregationOperation project = Aggregation.project("commentsList").andExclude("_id");
        Aggregation aggregation = Aggregation.newAggregation(match, unwind, group, project);
        AggregationResults<WallComments> result = mongoTemplate.aggregate(aggregation, "profileComments", WallComments.class);
        if (result == null) {
            return null;
        }
        WallComments wallComments = result.getUniqueMappedResult();
        if (wallComments == null) {
            return null;
        }
        Collections.sort(wallComments.getCommentsList(), new CommentCreationDateDescComparator());
        return wallComments;
    }
    
    public void deletePrivateComments(String userEmail, Comment comment) {
        ProfileComments wallComments = repository.findCommentsByUserEmail(userEmail);
        wallComments.getCommentsList().remove(comment);
        insert(wallComments);
    }
}
