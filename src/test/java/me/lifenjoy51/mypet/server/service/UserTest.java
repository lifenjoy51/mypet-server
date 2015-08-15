package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by lifenjoy51 on 2015-08-15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class UserTest {

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
        //먼저 사용자를 생성해야한다.
        User u = newUser();
        //펫 생성!
        Pet mp = newMyPet();
        //사용자에게 입양시키구.
        u.adoptPet(mp);
        //이야기를 하나 작성한다.
        Story s = newStory();
        //이야기를 쓴다!
        u.writeStory(s);

        //검증과정.
        // - 공원에 이야기가 있는지 확인한다.
        //TODO  공원에 이야기가 있는지 확인하는 방법은?
        //다른 사용자로 받아온다!
        User au = newUser();
        //다른 사용자의 펫!
        Pet ap = newAnotherPet();
        // 역시 입양시키고
        au.adoptPet(ap);
        //다른사용자가 기존 사용자를 볼 수 있는지 확인한다.
        List<AnothersPet> anothersPetList = au.listAnothersPets();
        assertThat(anothersPetList, hasItem(mp));
        // - 내 보관함에 있는지 확인한다.
        Story myStory = u.readMyStory(s);
        assertNotNull(myStory);
    }

    private Pet newAnotherPet() {
        return null;
    }

    private Pet newMyPet() {
        return null;
    }

    /**
     * 새로운 이야기를 작성한다.
     *
     * @return
     */
    private Story newStory() {
        return null;
    }

    /**
     * 새로운 사용자를 생성한다.
     *
     * @return
     */
    private User newUser() {
        return null;
    }

    @Test
    public void testListAnothersPets() throws Exception {

    }

    @Test
    public void testReadAnothersStory() throws Exception {

    }

    @Test
    public void testReadMyStory() throws Exception {

    }

    @Test
    public void testReadReplyWrittenByMe() throws Exception {

    }

    @Test
    public void testWriteReplyToAnothersStory() throws Exception {

    }
}