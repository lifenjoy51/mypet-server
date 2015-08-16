package me.lifenjoy51.mypet.server.service;

import lombok.Data;
import me.lifenjoy51.mypet.server.domain.id.PetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@Data
public abstract class Pet implements MyPet<PetId>, AnothersPet<PetId> {

    //기본관리자.
    @Autowired
    @Qualifier("NormalAdmin")
    Admin admin;

    Story story;

    User owner;

    List<Reply> replyList;

    public Pet() {
        replyList = new ArrayList<>();
    }

    @Override
    public Story readStory() {
        return story;
    }

    @Override
    public void writeReply(Reply reply) {

    }

    @Override
    public void deliverStory(Story story) {
        //전달할 이야기를 우선 간직하고.
        this.story = story;
        //공원을 찾아서.
        Park park = admin.findPark(this.getId());
        // 자기자신을 공원에 등록한다.
        park.registerPet(this);
    }

    @Override
    public Park getPark() {
        //Assert.notNull(park, "애완동물이 아직 공원으로 떠나지 않았습니다. 이야기를 작성해 공원으로 보내보세요.");
        Park park = admin.findPark(this.getId());
        return park;
    }

    @Override
    public List<Reply> readReplies() {
        return replyList;
    }

}
