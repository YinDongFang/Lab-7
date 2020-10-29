/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author harshalneelkamal
 */
public class Post {
    
    private int postId;
    private int userId;
    private int totalLike;
    private List<Comment> comments;

    public Post(int postId, int userId) {
        this.postId = postId;
        this.userId = userId;
        this.comments = new ArrayList<>();
    }

    public int getPostId() {
        return postId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
    
}
