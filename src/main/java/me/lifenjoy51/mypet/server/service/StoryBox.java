package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.id.StoryId;

/**
 */
public  interface StoryBox 
{
	/**	 */
	
	public Reply getReply() ;
	
	public Story getStory(StoryId storyId) ;
	
	/**	 */
	
	public void saveReply(Reply reply) ;
	
	/**	 */
	
	public void saveStory(Story story) ;
	
	
}

