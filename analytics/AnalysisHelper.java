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
        List<Post> list = new ArrayList<Post>(DataStore.getInstance().getPosts().values());
        list.sort(new Comparator<Post>() {
            public int compare(Post p1, Post p2) {
                return Integer.compare(p2.getTotalLike(), p1.getTotalLike());
            }
        });
        Post post = list.get(0);

        System.out.println("the post with most liked comments(" + post.getTotalLike() + " likes): " + post.getPostId());
    }

    // 3. Find the post with most comments.
    public void getPostWithMostComments() {
        List<Post> list = new ArrayList<Post>(DataStore.getInstance().getPosts().values());
        list.sort(new Comparator<Post>() {
            public int compare(Post p1, Post p2) {
                return Integer.compare(p2.getComments().size(), p1.getComments().size());
            }
        });
        Post post = list.get(0);

        System.out.println(
                "the post with most comments(" + post.getComments().size() + " comments): " + post.getPostId());
    }

    // 4. Top 5 inactive users based on total posts number.
    public List<User> getTop5InactiveUsersOnTotalPostsNumber() {
        List<User> list = new ArrayList<User>(DataStore.getInstance().getUsers().values());
        list.sort(new Comparator<User>() {
            public int compare(User p1, User p2) {
                return Integer.compare(p1.getPosts().size(), p2.getPosts().size());
            }
        });
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            top5.add(list.get(i));
            System.out.println("the Top" + (i + 1) + " inactive users based on total posts number: " + list.get(i));
        }
        return top5;
    }

    // 5. Top 5 inactive users based on total comments they created.
    public List<User> getTop5InactiveUsersOnTotalComments() {
        List<User> list = new ArrayList<User>(DataStore.getInstance().getUsers().values());
        list.sort(new Comparator<User>() {
            public int compare(User p1, User p2) {
                return Integer.compare(p1.getComments().size(), p2.getComments().size());
            }
        });
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            top5.add(list.get(i));
            System.out.println("the Top" + (i + 1) + " inactive users based on total posts number: " + list.get(i));
        }
        return top5;
    }

    // 6. Top 5 inactive users overall (sum of comments, posts and likes)
    public List<User> getTop5InactiveUsersOverall() {
        List<User> list = new ArrayList<User>(DataStore.getInstance().getUsers().values());
        list.sort(new Comparator<User>() {
            public int compare(User p1, User p2) {
                int t1 = p1.getComments().size() + p1.getPosts().size() + p1.getTotalLike();
                int t2 = p2.getComments().size() + p2.getPosts().size() + p2.getTotalLike();
                return Integer.compare(t1, t2);
            }
        });
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            top5.add(list.get(i));
            System.out.println("the Top" + (i + 1) + " inactive users overall: " + list.get(i));
        }
        return top5;
    }

    // 7. Top 5 proactive users overall (sum of comments, posts and likes)
    public List<User> getTop5ProactiveUsersOverall() {
        List<User> list = new ArrayList<User>(DataStore.getInstance().getUsers().values());
        list.sort(new Comparator<User>() {
            public int compare(User p1, User p2) {
                int t1 = p1.getComments().size() + p1.getPosts().size() + p1.getTotalLike();
                int t2 = p2.getComments().size() + p2.getPosts().size() + p2.getTotalLike();
                return Integer.compare(t2, t1);
            }
        });
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            top5.add(list.get(i));
            System.out.println("the Top" + (i + 1) + " proactive users overall: " + list.get(i));
        }
        return top5;
    }

}
