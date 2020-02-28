package com.khwinana.matsobane.assessment;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.logging.Level;

import static com.khwinana.matsobane.assessment.UserBuilder.buildUsers;

/**
 * TwitterFeed - The program receives two seven-bit ASCII files.
 *      The first file contains a list of users and their followers.
 *      The second file contains tweets.
 *      Given the users, followers and tweets, the program is to display a simulated twitter feed for each user to the
 *      console.
 *
 * @author matsobane
 */
@Log
@Setter @Getter
public class TwitterFeed
{

    private FeedPrinter feedPrinter;

    public static void main(String[] args )
    {
        log.log(Level.INFO, "Tweet Feed!" );

        if(args.length != 2){
            log.log(Level.SEVERE,"Both user.txt and tweets.txt file paths must be provided");
            log.log(Level.INFO,"Usage: java -jar twitter-feed-<version>.jar <absolute path>/user.txt <absolute path>/tweets.txt");
            log.log(Level.INFO,"See README.md file for example");

            System.exit(0);
        }

        String userDotTxt = args[0];
        String tweetDotTxt = args[1];

        TwitterFeed twitterFeed = new TwitterFeed();
        twitterFeed.setFeedPrinter(new FeedPrinter());

        SortedSet<User> users = buildUsers(getUserLines(userDotTxt));
        if(!users.isEmpty()) {

            List<String> tweetLines = getTweetLines(tweetDotTxt);

            Map<String, Set<Tweet>> userTweets = TweetBuilder.buildTweets(tweetLines);

            if (!userTweets.isEmpty()) {
                for (User user:users) {
                    for(String name: userTweets.keySet()) {
                        if(name.equals(user.getName())) {
                            user.getTweets().addAll(userTweets.get(name));
                        }
                    }
                }

                twitterFeed.getFeedPrinter().print(users);
            }else {
                throw new IllegalStateException("No tweets found");
            }
        }else {
            throw new IllegalStateException("No users found");
        }

    }

    private static List<String> getUserLines(String fileName) {
        log.info("Loading users from: " + fileName);
        return readFileLineByLine(fileName);
    }

    private static List<String> getTweetLines(String fileName) {
        log.info("Loading tweets from: " + fileName);
        return readFileLineByLine(fileName);
    }

    private static List<String> readFileLineByLine(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            log.log(Level.SEVERE,"Failed to read the file [%s] provided.\n Error: %s",e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
