package me.lifenjoy51.mypet.server.service;

import lombok.Data;
import me.lifenjoy51.mypet.server.domain.NormalStoryBox;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Data
public abstract class AbstractUser implements User {
    
    int id;
    
    String name;

    /**
     * 애완동물!
     */
    Map<Integer, Pet> pets;

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
        this.storyBox = new NormalStoryBox();
        this.pets = new HashMap<>();
    }
    
    
    @Override
    public int adoptPet(Pet pet) {
        this.pets.put(pet.getId(), pet);
        return pet.getId();
    }

    @Override
    public List<Pet> getPets() {
        return pets.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    @Override
    public Pet callPet(int petId) {
        return pets.get(petId);
    }

    @Override
    public void writeStory(Story story) {
        Assert.isTrue(!CollectionUtils.isEmpty(pets), "애완동물이 없습니다! 애완동물을 먼저 입양하세요!");
        //애완동물이 내가 쓴 이야기를 들고간다.
        this.pets.get(story.getPet().getId()).deliverStory(story);
        //작성한 이야기는 보관함에 저장한다.
        storyBox.saveStory(story);
    }
    
    @Override
    public List<AnothersPet> listAnothersPets(int petId){
        Assert.notNull(CollectionUtils.isEmpty(pets), "애완동물이 없습니다! 애완동물을 먼저 입양하세요!");
        //애완동물에게 찾아간 공원을 물어본다.
        Park park = this.pets.get(petId).getPark();
        //공원에 있는 다른사람의 애완동물을 받아온다.
        List<AnothersPet> anothersPets = park.findAnothersPets(this);
        
        return anothersPets;
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
