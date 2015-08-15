package me.lifenjoy51.mypet.server.service;

import java.util.Collection;
import java.util.List;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
public abstract class AbstractUser implements User {

    /**
     * 애완동물!
     */
    Pet pet;

    /**
     * 내가 작성한 이야기를 담는곳.
     */
    StoryBox storyBox;

    /**
     * 내가 작성한 답글들을 담는 곳.
     */
    ReplyBox replyBox;

    @Override
    public void writeStory(Story story) {
        //애완동물이 내가 쓴 이야기를 들고간다.
        pet.deliverStory(story);
        //작성한 이야기는 보관함에 저장한다.
        storyBox.saveStory(story);
    }
    
    @Override
    public List<AnothersPet> listAnothersPets(){
        //애완동물에게 찾아간 공원을 물어본다.
        Park park = pet.getPark();
        //공원에 있는 다른사람의 애완동물을 받아온다.
        List<AnothersPet> anothersPets = park.findAnothersPet(this);
        
        return anothersPets;
    }

    @Override
    public Story readAnothersStory(AnothersPet anothersPet) {
        //애완동물에게 찾아간 공원을 물어본다.
        Park park = pet.getPark();
        //하나의 이야기를 받아온다.
        Story anothersStory = park.getAnothersStory(anothersPet);
        return null;
    }

    @Override
    public Story readMyStory(Story s) {
        return null;
    }

    @Override
    public ReplyWrittenByMe readReplyWrittenByMe() {
        return null;
    }

    @Override
    public void writeReplyToAnothersStory(Reply parameter) {

    }
}
