package com.khwinana.matsobane.assessment;

import java.util.*;

public class TweetBuilder {

    public static Map<String,Set<Tweet>> buildTweets(List<String> tweetLines){
        if(tweetLines==null || tweetLines.isEmpty()){
            return null;
        }

        Map<String,Set<Tweet>> tweets = new HashMap<>();

        for (String line:tweetLines) {
            int i = line.indexOf('>');
            String user = line.substring(0,i);
            String text = line.substring(i+1);
            if(tweets.containsKey(user)){
                tweets.get(user).add(new Tweet(text.trim(),System.currentTimeMillis()));
            }else {
                tweets.put(user,new HashSet<>(Arrays.asList(new Tweet(text.trim(),System.currentTimeMillis()))));
            }
        }

        return tweets;
    }
}
