package me.lifenjoy51.mypet.server.domain;

import me.lifenjoy51.mypet.server.domain.id.ReplyId;
import me.lifenjoy51.mypet.server.service.Reply;
import me.lifenjoy51.mypet.server.service.ReplyBox;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lifenjoy51 on 2015-08-16.
 */
public class NormalReplyBox implements ReplyBox {

    Map<ReplyId, Reply> replyMap;
    
    public NormalReplyBox(){
        replyMap = new HashMap<>();
    }
    
    
    @Override
    public Reply getReplyWrittenByMe(ReplyId replyId) {
        return replyMap.get(replyId);
    }

    @Override
    public void saveReplyWrittenByMe(Reply reply) {
        replyMap.put(reply.getId(), reply);
    }
}
