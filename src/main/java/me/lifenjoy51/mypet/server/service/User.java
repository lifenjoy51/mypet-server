package me.lifenjoy51.mypet.server.service;

import java.util.List;

/**
 * 사용자.
 */
public interface User extends Identifiable{

    /**
     * 애완동물을 입양한다.
     * @param pet
     */
    public int adoptPet(Pet pet);    
    public List<Pet> getPets();    
    public Pet callPet(int petId);

    public Story readMyStory(int storyId);
    public ReplyWrittenByMe readReplyWrittenByMe();

    public List<AnothersPet> listAnothersPets(int petId);
    public void writeReplyToAnothersStory(Reply reply);
    
    public void writeStory(Story story);
}

