package me.lifenjoy51.mypet.server.service;

import java.util.List;

/**
 */
public interface MyPet<T> extends Identifiable<T> {
	/**	 */
	
	public void deliverStory(Story story) ;
	
	/**	 */
	
	public Park getPark() ;
	
	/**	 */
	
	public List<Reply> readReplies() ;
	
	
}

