package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.id.ReplyId;

/**
 */
public  interface ReplyBox
{
	/**	 */
	
	public Reply getReplyWrittenByMe(ReplyId replyId) ;
	
	/**	 */
	
	public void saveReplyWrittenByMe(Reply reply) ;
	
	
}

