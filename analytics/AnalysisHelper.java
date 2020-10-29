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
        // 统计post对应的all likes数量
        Map<Post, Integer> countMap = new HashMap<Post, Integer>();
        Iterator<Post> iterator = DataStore.getInstance().getPosts().values().iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            int total = 0;
            for (Comment comment : post.getComments()) {
                total += comment.getLikes();
            }
            countMap.put(post, total);
        }
        // 按照total降序排列
        List<Map.Entry<Post, Integer>> list = new ArrayList<Map.Entry<Post, Integer>>(countMap.entrySet());
        Comparator<Map.Entry<Post, Integer>> comparator = new Comparator<Map.Entry<Post, Integer>>() {
            public int compare(Map.Entry<Post, Integer> c1, Map.Entry<Post, Integer> c2) {
                return Integer.compare(c2.getValue(), c1.getValue());
            }
        };
        Collections.sort(list, comparator);
        Post post = list.get(0).getKey();
        int total = list.get(0).getValue();

        System.out.println("the post with most liked comments(" + total + " likes): " + post.getPostId());

    }

    // 3. Find the post with most comments.
    public void getPostWithMostComments() {
        // 按照comments数量降序排列posts
        List<Post> posts = new ArrayList<Post>(DataStore.getInstance().getPosts().values());
        Comparator<Post> comparator = new Comparator<Post>() {
            public int compare(Post c1, Post c2) {
                return Integer.compare(c2.getComments().size(), c1.getComments().size());
            }
        };
        Collections.sort(posts, comparator);
        Post post = posts.get(0);

        System.out.println("the post with most liked comments(" + post.getComments().size() + "): " + post.getPostId());

    }

    // 4. Top 5 inactive users based on total posts number.
    public List<User> getTop5InactiveUsersOnTotalPostsNumber() {
        // 统计每个user对应的post数量
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        Iterator<Post> iterator = DataStore.getInstance().getPosts().values().iterator();
        while (iterator.hasNext()) {
            int userId = iterator.next().getUserId();
            int count = countMap.containsKey(userId) ? countMap.get(userId) : 0;
            countMap.put(userId, count + 1);
        }
        // 按照post数量升序排列
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(countMap.entrySet());
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> c1, Map.Entry<Integer, Integer> c2) {
                return Integer.compare(c1.getValue(), c2.getValue());
            }
        };
        Collections.sort(list, comparator);
        // 获取top5并且打印出来
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            int userId = list.get(i).getKey();
            User user = DataStore.getInstance().getUsers().get(userId);
            top5.add(user);

            System.out.println("the top" + (i + 1) + " inactive user based on total posts number is: " + user + " with "
                    + list.get(i).getValue() + " posts.");
        }
        return top5;
    }

    // 5. Top 5 inactive users based on total comments they created.
    public List<User> getTop5InactiveUsersOnTotalComments() {
        // 获取user列表
        List<User> users = new ArrayList<User>(DataStore.getInstance().getUsers().values());
        // 按照comments数量升序排列
        Comparator<User> comparator = new Comparator<User>() {
            public int compare(User c1, User c2) {
                return Integer.compare(c1.getComments().size(), c2.getComments().size());
            }
        };
        Collections.sort(users, comparator);
        // 获取top5
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, users.size());
        for (int i = 0; i < count; i++) {
            User user = users.get(i);
            top5.add(user);

            System.out.println("the top" + (i + 1) + " inactive user based on total comments they created is: " + user
                    + " with " + user.getComments().size() + " comments.");
        }
        return top5;
    }

    // 6. Top 5 inactive users overall (sum of comments, posts and likes)
    public List<User> getTop5InactiveUsersOverall() {
        // 统计comments和likes数量
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        Iterator<User> iterator = DataStore.getInstance().getUsers().values().iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            int count = user.getComments().size();
            for (Comment comment : user.getComments()) {
                count += comment.getLikes();
            }
            countMap.put(user.getId(), count);
        }
        // 加上每个user对应的post数量
        Iterator<Post> postIterator = DataStore.getInstance().getPosts().values().iterator();
        while (postIterator.hasNext()) {
            int userId = postIterator.next().getUserId();
            countMap.put(userId, countMap.get(userId) + 1);
        }
        // 按照count数量升序排列
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(countMap.entrySet());
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> c1, Map.Entry<Integer, Integer> c2) {
                return Integer.compare(c1.getValue(), c2.getValue());
            }
        };
        Collections.sort(list, comparator);
        // 获取top5并且打印出来
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            int userId = list.get(i).getKey();
            User user = DataStore.getInstance().getUsers().get(userId);
            top5.add(user);

            System.out.println("the top" + (i + 1) + " inactive user overall: " + user + " with "
                    + list.get(i).getValue() + " overall.");
        }
        return top5;
    }

    // 7. Top 5 proactive users overall (sum of comments, posts and likes)
    public List<User> getTop5ProactiveUsersOverall() {
        // 统计comments和likes数量
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        Iterator<User> iterator = DataStore.getInstance().getUsers().values().iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            int count = user.getComments().size();
            for (Comment comment : user.getComments()) {
                count += comment.getLikes();
            }
            countMap.put(user.getId(), count);
        }
        // 加上每个user对应的post数量
        Iterator<Post> postIterator = DataStore.getInstance().getPosts().values().iterator();
        while (postIterator.hasNext()) {
            int userId = postIterator.next().getUserId();
            countMap.put(userId, countMap.get(userId) + 1);
        }
        // 按照count数量降序排列
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(countMap.entrySet());
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> c1, Map.Entry<Integer, Integer> c2) {
                return Integer.compare(c2.getValue(), c1.getValue());
            }
        };
        Collections.sort(list, comparator);
        // 获取top5并且打印出来
        List<User> top5 = new ArrayList<User>();
        int count = Math.min(5, list.size());
        for (int i = 0; i < count; i++) {
            int userId = list.get(i).getKey();
            User user = DataStore.getInstance().getUsers().get(userId);
            top5.add(user);

            System.out.println("the top" + (i + 1) + " proactive user overall: " + user + " with "
                    + list.get(i).getValue() + " overall.");
        }
        return top5;
    }

}
