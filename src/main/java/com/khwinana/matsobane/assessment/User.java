package com.khwinana.matsobane.assessment;

import lombok.*;

import java.util.*;

@ToString
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String name;
    private Set<User> follows = new HashSet<>();
    private Set<User> followers = new HashSet<>();
    private List<Tweet> tweets = new LinkedList<>();

    public void addFollower(User follower){
        if(follower== null){
            throw new IllegalArgumentException("Follower User cannot be null");
        }

        User u = this.followers.stream()
                .filter(user -> user.getName().equals(follower.getName()))
                .findAny()
                .orElse(null);
        if(u == null){
            this.followers.add(follower);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(follows, user.follows) &&
                Objects.equals(followers, user.followers) &&
                Objects.equals(tweets, user.tweets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, follows, followers, tweets);
    }
}
