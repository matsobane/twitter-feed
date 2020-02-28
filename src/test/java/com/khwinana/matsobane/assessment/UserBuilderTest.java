package com.khwinana.matsobane.assessment;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class UserBuilderTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUserLinesAreEmpty(){
        //Given
        List<String> userLines = Collections.emptyList();

        //When
        UserBuilder.buildUsers(userLines);
    }


    @Test
    public void shouldContainThreeUsers(){
        //Given
        List<String> userLines = Arrays.asList("Ward follows Alan", "Alan follows Martin", "Ward follows Martin, Alan");

        //When
        SortedSet<User> users = UserBuilder.buildUsers(userLines);

        //Then
        assertNotNull(users);
        //And
        assertTrue(users.size() == 3);
    }

}
