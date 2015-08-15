package me.lifenjoy51.mypet.server.domain;

import me.lifenjoy51.mypet.server.service.Reply;
import me.lifenjoy51.mypet.server.service.Story;
import me.lifenjoy51.mypet.server.service.StoryBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
public class NormalStoryBox implements StoryBox {
    
    Map<Integer, Story> storyMap;
    
    public NormalStoryBox(){
        storyMap = new HashMap<>();
    }
    
    @Override
    public Reply getReply() {
        return null;
    }

    @Override
    public Story getStory(int storyId) {
        return storyMap.get(storyId);
    }

    @Override
    public void saveReply(Reply parameter) {

    }

    @Override
    public void saveStory(Story story) {
        storyMap.put(story.getId(), story);
    }
}
