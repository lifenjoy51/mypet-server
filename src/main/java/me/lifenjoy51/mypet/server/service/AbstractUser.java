package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.NormalStoryBox;
import org.springframework.util.Assert;

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
    
    public AbstractUser(){
        //이야기 상자와 답글 상자를 만든다.
        storyBox = new NormalStoryBox();
    }
    
    
    @Override
    public void adoptPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public void writeStory(Story story) {
        Assert.notNull(pet, "애완동물이 없습니다! 애완동물을 먼저 입양하세요!");
        //애완동물이 내가 쓴 이야기를 들고간다.
        pet.deliverStory(story);
        //작성한 이야기는 보관함에 저장한다.
        storyBox.saveStory(story);
    }
    
    @Override
    public List<AnothersPet> listAnothersPets(){
        Assert.notNull(pet, "애완동물이 없습니다! 애완동물을 먼저 입양하세요!");
        //애완동물에게 찾아간 공원을 물어본다.
        Park park = pet.getPark();
        //공원에 있는 다른사람의 애완동물을 받아온다.
        List<AnothersPet> anothersPets = park.findAnothersPets(this);
        
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
    public Story readMyStory(int storyId) {
        return storyBox.getStory(storyId);
    }

    @Override
    public ReplyWrittenByMe readReplyWrittenByMe() {
        return null;
    }

    @Override
    public void writeReplyToAnothersStory(Reply parameter) {

    }
}
