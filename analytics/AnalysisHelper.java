/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.analytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author harshalneelkamal
 */

import java.util.Map;
import lab7.entities.Comment;
import lab7.entities.Post;
import lab7.entities.User;

public class AnalysisHelper {
    // 1. Find Average number of likes per comment.
    public void getAverageLikesPerCommets() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likeNumber = 0;
        int commentNumber = comments.size();
        for (Comment c : comments.values()) {
            likeNumber += c.getLikes();
        }

        System.out.println("average of likes per comments: " + likeNumber / commentNumber);

    }

    // 2. Find the post with most liked comments.
    public void getPostWithMostLikedComments() {
        // TODO:
    }

    // 3. Find the post with most comments.
    public void getPostWithMostComments() {
        // TODO:
    }

    // 4. Top 5 inactive users based on total posts number.
    public List<User> getTop5InactiveUsersOnTotalPostsNumber() {
        // TODO:
    }

    // 5. Top 5 inactive users based on total comments they created.
    public List<User> getTop5InactiveUsersOnTotalComments() {
        // TODO:
    }

    // 6. Top 5 inactive users overall (sum of comments, posts and likes)
    public List<User> getTop5InactiveUsersOverall() {
        // TODO:
    }

    // 7. Top 5 proactive users overall (sum of comments, posts and likes)
    public List<User> getTop5ProactiveUsersOverall() {
        // TODO:
    }

}
