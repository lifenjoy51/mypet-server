package me.lifenjoy51.mypet.server.service;
/**
 */
public interface MyPet<T> extends Identifiable<T> {
	/**	 */
	
	public void deliverStory(Story story) ;
	
	/**	 */
	
	public Park getPark() ;
	
	/**	 */
	
	public void readReply() ;
	
	
}

