package com.khwinana.matsobane.assessment;

import lombok.extern.java.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

@Log
public class FeedPrinter {
    public void print(Set<User> users){

        if(users == null || users.isEmpty()){
            throw new IllegalArgumentException("Users cannot be null");
        }

        log.log(Level.INFO,"=============================== Twitter Feed =================================");
        for (User u:users) {
            System.out.println(u.getName());
            Set<Tweet> tweets = toTweets(u);
            if(!tweets.isEmpty()){
                for (Tweet t: tweets) {
                    System.out.printf("\t @%s: %s\n",u.getName(),t.getText());
                }
            }

        }
    }

    private Set<Tweet> toTweets(User u) {
        if(u == null)
            return null;
        Set<Tweet> tweets = new HashSet<>();
        if(!u.getTweets().isEmpty()){
            tweets.addAll(u.getTweets());
        }

        if(!u.getFollows().isEmpty()){
            for (User follow: u.getFollows()) {
                if(!follow.getTweets().isEmpty()){
                    tweets.addAll(follow.getTweets());
                }
            }
        }

        return tweets;
    }

}
