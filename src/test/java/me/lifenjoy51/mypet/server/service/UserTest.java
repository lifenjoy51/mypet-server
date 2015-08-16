package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.Application;
import me.lifenjoy51.mypet.server.domain.NormalPet;
import me.lifenjoy51.mypet.server.domain.NormalReply;
import me.lifenjoy51.mypet.server.domain.NormalStory;
import me.lifenjoy51.mypet.server.domain.NormalUser;
import me.lifenjoy51.mypet.server.domain.id.PetId;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("scratch")
@Transactional
public class UserTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AutowireCapableBeanFactory beanFactory;

    /**
     * 이야기를 작성한 사용자 객체를 만든다.
     * 사용자, 애완동물, 이야기 객체가 담겨있다.
     * @return
     */
    private UserWhoWroteStory newUserWhoWroteStory(){

        //먼저 사용자를 생성해야한다.
        User user = newUser();
        //펫 생성!
        Pet pet = newPet(Integer.parseInt(RandomStringUtils.randomNumeric(5)));
        //사용자에게 입양시키구.
        user.adoptPet(pet);
        //이야기를 하나 작성한다.
        Story story = newStory(pet);
        //이야기를 쓴다!
        user.writeStory(story);

        UserWhoWroteStory form = new UserWhoWroteStory(user, pet, story);
        return form;
    }

    /**
     * 애완동물 생성! 
     * @param seq
     * @return
     */
    private Pet newPet(int seq) {
        PetId id= new PetId(seq);
        String name = RandomStringUtils.randomAlphabetic(5);
        Pet p = new NormalPet(id, name);
        beanFactory.autowireBean(p);
        return p;
    }

    /**
     * 새로운 이야기를 작성한다.
     *
     * @return
     */
    private Story newStory(Pet p) {
        String text = TestUtil.randomStoryText();
        Story s = new NormalStory(p, text);
        return s;
    }

    /**
     * 새로운 사용자를 생성한다.
     *
     * @return
     */
    private User newUser() {
        User u = new NormalUser();
        return u;
    }

    /**
     * 새로운 답장.
     * @param anotherStory
     * @return
     */
    private Reply newReply(Story anotherStory) {
        String contents = TestUtil.randomReplyText();
        Reply reply = new NormalReply(anotherStory, contents);
        return reply;
    }

    /**
     * 이야기 작성 테스트. <br/>
     * - 이야기를 작성하면 애완동물이 이야기를 공원으로 들고간다. <br/>
     * - 공원에 내 애완동물이 있는지 확인한다. <br/>
     * - 내 이야기는 이야기 보관함에 들어가 있어야 한다. <br/>
     *
     * @throws Exception
     */
    @Test
    public void testWriteStory() throws Exception {

        UserWhoWroteStory one = newUserWhoWroteStory();
        one.getUser().writeStory(one.getStory());

        //검증과정.
        // - 공원에 이야기가 있는지 확인한다.
        //다른 사용자로 받아온다!
        UserWhoWroteStory another = newUserWhoWroteStory();
        another.getUser().writeStory(another.getStory());
        
        //다른사용자가 기존 사용자를 볼 수 있는지 확인한다.
        List<AnothersPet> anotherPetList = another.getUser().listAnothersPets(another.getPet().getId());
        log.debug("anotherPetList => {}", anotherPetList);
        assertThat(anotherPetList, hasItem(one.getPet()));
        assertThat(anotherPetList, not(hasItem(another.getPet())));
        // - 내 보관함에 있는지 확인한다.
        Story myStory = one.getUser().readMyStory(one.getStory().getId());
        assertNotNull(myStory);
    }

    @Test
    public void testListAnothersPets() throws Exception {
        //먼저 사용자를 생성해야한다.
        //이야기를 쓴다!
        UserWhoWroteStory one = newUserWhoWroteStory();
        one.getUser().writeStory(one.getStory());

        //다른 사용자 1
        //이야기를 쓴다!
        UserWhoWroteStory another1 = newUserWhoWroteStory();
        another1.getUser().writeStory(another1.getStory());

        //다른 사용자 2
        //이야기를 쓴다!
        UserWhoWroteStory another2 = newUserWhoWroteStory();
        another2.getUser().writeStory(another2.getStory());
        
        //사용자가 다른 사용자 1,2의 이야기를 볼 수 있는지 확인한다.
        List<AnothersPet> anotherPetList = one.getUser().listAnothersPets(one.getPet().getId());
        log.debug("anotherPetList => {}", anotherPetList);
        assertThat(anotherPetList, hasItem(another1.getPet()));
        assertThat(anotherPetList, hasItem(another2.getPet()));
        assertThat(anotherPetList, not(hasItem(one.getPet())));
        
    }

    @Test
    public void testWriteReplyToAnothersStory() throws Exception {
        //먼저 사용자를 생성해야한다.
        //이야기를 쓴다!
        UserWhoWroteStory one = newUserWhoWroteStory();
        one.getUser().writeStory(one.getStory());

        //다른 사용자 1
        //이야기를 쓴다!
        UserWhoWroteStory another1 = newUserWhoWroteStory();
        another1.getUser().writeStory(another1.getStory());

        //다른 사용자 2
        //이야기를 쓴다!
        UserWhoWroteStory another2 = newUserWhoWroteStory();
        another2.getUser().writeStory(another2.getStory());

        //다른 사용자의 이야기를 받아온다.
        List<AnothersPet> anotherPetList = one.getUser().listAnothersPets(one.getPet().getId());
        
        //다른 사용자의 이야기 하나를 읽고
        Story anotherStory = anotherPetList.get(0).readStory();
        User receivedStoryOwner = anotherStory.getPet().getOwner();
        
        //답장한다.
        Reply reply = newReply(anotherStory);
        one.getUser().writeReplyToAnothersStory(reply);
        
        //원래 주인이 답장을 읽을 수 있다.
        List<Reply> receivedReplies = receivedStoryOwner.getPet(anotherStory.getPet().getId()).readReplies();
        assertThat(receivedReplies, hasItem(reply));
        
        //내가 작성한 답글을 확인한다.
        Reply writtenByMe = one.getUser().readReplyWrittenByMe(reply.getId());
        assertEquals(reply.getContents(), writtenByMe.getContents());

    }
}