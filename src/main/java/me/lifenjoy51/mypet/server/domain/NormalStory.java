package me.lifenjoy51.mypet.server.domain;

import lombok.Data;
import me.lifenjoy51.mypet.server.service.Pet;
import me.lifenjoy51.mypet.server.service.Reply;
import me.lifenjoy51.mypet.server.service.Story;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Data
public class NormalStory implements Story {
    
    int id;
    String name;
    Pet pet;
    Reply reply;
    
    NormalStory(){
    }
    
    public NormalStory(Pet pet){
        this.pet = pet;
    }
    
    @Override
    public Reply getReply() {
        return reply;
    }
    
    @Override
    public Pet getPet() {
        return pet;
    }
}
