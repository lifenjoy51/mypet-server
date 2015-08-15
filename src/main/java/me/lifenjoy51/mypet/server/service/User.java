package me.lifenjoy51.mypet.server.service;

import java.util.Collection;

/**
 * 사용자.
 */
public interface User {

    public Collection<AnothersPet> listAnothersPets();

    public Story readAnothersStory(AnothersPet parameter);

    public Story readMyStory();

    public ReplyWrittenByMe readReplyWrittenByMe();

    public void writeReplyToAnothersStory(Reply parameter);

    public void writeStory(Story parameter);
}

