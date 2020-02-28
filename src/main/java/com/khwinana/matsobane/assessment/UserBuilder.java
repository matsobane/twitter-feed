package com.khwinana.matsobane.assessment;

import java.util.*;

public class UserBuilder {
    public static final String COMMA = ",";
    public static final String SPACE_FOLLOWS_SPACE = " follows ";

    public static SortedSet<User> buildUsers(List<String> userLines) {
        if(userLines == null || userLines.isEmpty()){
            throw new IllegalArgumentException("userLines cannot be null");
        }

        SortedSet<User> users = new TreeSet<>((o1, o2) -> {
            if(o1.getName() == o2.getName())
                return 0;
            if(o1.getName() == null ){
                return -1;
            }

            if(o2.getName() == null){
                return 1;
            }
            return o1.getName().compareTo(o2.getName());
        });

        for (String line: userLines) {
            String[] lineSplit = line.split(SPACE_FOLLOWS_SPACE);
            final String name = lineSplit[0];

            User follower = users.stream()
                    .filter(user -> name.equals(user.getName()))
                    .findAny()
                    .orElse(null);

            if(follower==null){
                follower = new User();
                follower.setName(name);
                users.add(follower);
            }
            addFollowsToFollower(line, lineSplit[1], follower);

            addFollowsToAllUsers(users, follower.getFollows());
        }
        return users;
    }

    private static void addFollowsToAllUsers(Set<User> users, Set<User> follows) {
        if(!follows.isEmpty()){
            for (User follow : follows){
                User u = users.stream()
                        .filter(user -> follow.getName().equals(user.getName()))
                        .findAny()
                        .orElse(null);
                if(u == null){
                    users.add(follow);
                }
            }
        }
    }


    private static void addFollowsToFollower(String line, String lineAfterFollows, User follower) {

        Set<User> follows = extractFollowedUsersFromStringLine(lineAfterFollows);
        if(follows == null){
            throw new IllegalStateException("User line invalid: " + line);
        }

        Set<User> users = follower.getFollows();
        for (User follow :follows){
            User u = users.stream()
                    .filter(user -> follow.getName().equals(user.getName()))
                    .findAny()
                    .orElse(null);
            if(u == null){
                users.add(follow);
            }

        }

    }

    private static Set<User> extractFollowedUsersFromStringLine(String s) {
        if(s == null){
            return null;
        }

        Set<User> followed = new HashSet<>();

        String[] followedUserNames = s.contains(COMMA)? s.split(COMMA): new String[]{s};

        for (int i = 0; i < followedUserNames.length; i++) {
            User u = new User();
            u.setName(followedUserNames[i].trim());
            followed.add(u);
        }
        return followed;
    }
}
