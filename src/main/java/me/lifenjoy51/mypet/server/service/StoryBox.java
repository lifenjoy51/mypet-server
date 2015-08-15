package me.lifenjoy51.mypet.server.service;
/**
 */
public  interface StoryBox 
{
	/**	 */
	
	public Reply getReply() ;
	
	public Story getStory(int storyId) ;
	
	/**	 */
	
	public void saveReply(Reply parameter) ;
	
	/**	 */
	
	public void saveStory(Story parameter) ;
	
	
}

