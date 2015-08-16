package me.lifenjoy51.mypet.server.service;

import lombok.Data;

/**
 * Created by lifenjoy51 on 2015-08-16.
 */
@Data
public class UserWhoWroteStory {

    final User user;
    final Pet pet;
    final Story story;

    public UserWhoWroteStory(User user, Pet pet, Story story) {
        this.user = user;
        this.pet = pet;
        this.story = story;
    }
}
