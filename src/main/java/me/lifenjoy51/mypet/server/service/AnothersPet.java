package me.lifenjoy51.mypet.server.service;

/**
 */
public interface AnothersPet<T> extends Identifiable<T> {
    
    /**     */
    public Story readStory();

    /**     */
    public void writeReply(Reply reply);


}

