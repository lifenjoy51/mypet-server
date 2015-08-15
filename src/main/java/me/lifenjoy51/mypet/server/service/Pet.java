package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.domain.NormalAdmin;
import me.lifenjoy51.mypet.server.domain.id.PetId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
public abstract class Pet implements MyPet<PetId>, AnothersPet<PetId>{

    @Autowired
    @Qualifier("NormalAdmin")
    Admin admin;
    
    Story story;
    
    public Pet(){
        // 관리자는 우선 기본 관리자로.
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
    public void readReply() {

    }
    
}
