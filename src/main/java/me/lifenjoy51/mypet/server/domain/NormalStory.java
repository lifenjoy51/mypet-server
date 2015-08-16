package me.lifenjoy51.mypet.server.domain;

import lombok.Data;
import me.lifenjoy51.mypet.server.domain.id.ReplyId;
import me.lifenjoy51.mypet.server.domain.id.StoryId;
import me.lifenjoy51.mypet.server.service.Pet;
import me.lifenjoy51.mypet.server.service.Reply;
import me.lifenjoy51.mypet.server.service.Story;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Data
public class NormalStory implements Story {
    
    StoryId id;
    String name;
    Pet pet;
    Map<ReplyId, Reply> replyMap;
    
    String contents;
    LocalDateTime dt;
    
    NormalStory(){
        replyMap = new HashMap<>();
    }
    
    public NormalStory(Pet pet){
        this();
        this.pet = pet;
    }

    @Override
    public Reply getReply(ReplyId replyId) {
        return this.replyMap.get(replyId);
    }

    @Override
    public List<Reply> getAllReplies() {
        return this.replyMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public ReplyId saveReply(Reply reply) {
        return null;
    }

    @Override
    public Pet getPet() {
        return pet;
    }
}
