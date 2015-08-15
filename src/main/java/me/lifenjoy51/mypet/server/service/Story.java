package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.id.StoryId;

/**
 */
public  interface Story  extends Identifiable<StoryId>
{
	
	public Reply getReply() ;
	
	public Pet getPet();
	
}

