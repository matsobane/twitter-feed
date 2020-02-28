package com.khwinana.matsobane.assessment;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TweetBuilderTest {

    @Test
    public void shouldEscapeGreaterTweets(){
        //Given
        List<String> tweetLines = Arrays.asList("Alan> If you have more than 1 greater than signs (>), the program should still work.");
        //When
        Map<String,Set<Tweet>> tweets = TweetBuilder.buildTweets(tweetLines);

        //Then
        assertNotNull(tweets);
        //And
        assertFalse(tweets.isEmpty());
        //And
        assertTrue(containsTweetWithText(tweets.get("Alan"),"If you have more than 1 greater than signs (>), the program should still work."));
    }


    @Test
    public void shouldExtractTweets(){
        //Given
        List<String> tweetLines = Arrays.asList("Alan> If you have a procedure with 10 parameters, you probably missed some.",
                "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors.",
                "Alan> Random numbers should not be generated with a method chosen at random.");
        //When
        Map<String,Set<Tweet>> tweets = TweetBuilder.buildTweets(tweetLines);

        //Then
        assertNotNull(tweets);
        //And
        assertFalse(tweets.isEmpty());
        //And
        assertTrue(tweets.size()==2);
        //And
        assertTrue(tweets.containsKey("Ward"));
        //And
        assertTrue(containsTweetWithText(tweets.get("Alan"),"Random numbers should not be generated with a method chosen at random."));
    }

    private boolean containsTweetWithText(Set<Tweet> tweets,String text){
        boolean result = false;
        for (Tweet t:tweets) {
            if(text.equals(t.getText())){
                return true;
            }
        }

        return result;
    }
}
