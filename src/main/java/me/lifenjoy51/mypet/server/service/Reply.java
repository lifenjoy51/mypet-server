package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.id.ReplyId;

import java.time.LocalDateTime;

/**
 */
public  interface Reply extends Identifiable<ReplyId>, ContentsWritable {
	public Story getStory();
}

