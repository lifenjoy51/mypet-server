package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.id.PetId;
import me.lifenjoy51.mypet.server.domain.id.StoryId;
import me.lifenjoy51.mypet.server.domain.id.UserId;

import java.util.List;

/**
 * 사용자.
 */
public interface User extends Identifiable<UserId> {

    /**
     * 애완동물을 입양한다.
     *
     * @param pet
     */
    public PetId adoptPet(Pet pet);

    public List<Pet> getPets();

    public Pet callPet(PetId petId);

    public Story readMyStory(StoryId storyId);

    public ReplyWrittenByMe readReplyWrittenByMe();

    public List<AnothersPet> listAnothersPets(PetId petId);

    public void writeReplyToAnothersStory(Reply reply);

    public void writeStory(Story story);
}

