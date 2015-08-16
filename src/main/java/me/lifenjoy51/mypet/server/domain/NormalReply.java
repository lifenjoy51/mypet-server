package me.lifenjoy51.mypet.server.domain;

import lombok.Data;
import me.lifenjoy51.mypet.server.domain.id.ReplyId;
import me.lifenjoy51.mypet.server.service.ContentsWritable;
import me.lifenjoy51.mypet.server.service.Reply;
import me.lifenjoy51.mypet.server.service.Story;

import java.time.LocalDateTime;

/**
 * Created by lifenjoy51 on 2015-08-16.
 */
@Data
public class NormalReply implements Reply, ContentsWritable {
    
    ReplyId id;
    
    String name;
    
    Story story;
    
    String contents;

    LocalDateTime dt;

    public NormalReply(Story story, String contents) {
        this.story = story;
        this.contents = contents;
        this.dt = LocalDateTime.now();
    }
}
