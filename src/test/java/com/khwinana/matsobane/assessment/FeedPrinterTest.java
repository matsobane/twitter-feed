package com.khwinana.matsobane.assessment;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class FeedPrinterTest {

    private FeedPrinter feedPrinter;

    @Before
    public void init(){
        this.feedPrinter = new FeedPrinter();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenUserLinesAreEmpty() {
        //Given
        Set<User> users = Collections.emptySet();

        //When
        this.feedPrinter.print(users);

    }
}
