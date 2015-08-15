package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.id.ReplyId;
import me.lifenjoy51.mypet.server.domain.id.StoryId;

import java.util.List;

/**
 */
public  interface Story  extends Identifiable<StoryId>
{
	
	public Reply getReply(ReplyId replyId) ;
	
	public List<Reply> getAllReplies();
	
	public ReplyId saveReply(Reply reply);
	
	public Pet getPet();
	
}

