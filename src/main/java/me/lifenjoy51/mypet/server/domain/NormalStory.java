package me.lifenjoy51.mypet.server.domain;

import lombok.Data;
import me.lifenjoy51.mypet.server.service.Reply;
import me.lifenjoy51.mypet.server.service.Story;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Data
public class NormalStory implements Story {
    
    int id;
    String name;
    
    @Override
    public Reply getReply() {
        return null;
    }
}
