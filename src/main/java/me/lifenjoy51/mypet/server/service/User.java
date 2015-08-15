package me.lifenjoy51.mypet.server.service;

import java.util.Collection;
import java.util.List;

/**
 * 사용자.
 */
public interface User {

    /**
     * 애완동물을 입양한다.
     * @param pet
     */
    public void adoptPet(Pet pet);

    public List<AnothersPet> listAnothersPets();

    public Story readAnothersStory(AnothersPet anothersPet);

    public Story readMyStory(Story story);

    public ReplyWrittenByMe readReplyWrittenByMe();

    public void writeReplyToAnothersStory(Reply reply);

    public void writeStory(Story story);
}

