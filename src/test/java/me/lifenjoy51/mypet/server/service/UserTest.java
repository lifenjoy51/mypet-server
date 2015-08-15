package me.lifenjoy51.mypet.server.service;

import me.lifenjoy51.mypet.server.Application;
import me.lifenjoy51.mypet.server.domain.NormalPet;
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
        Pet mp = newMyPet(1);
        //사용자에게 입양시키구.
        u.adoptPet(mp);
        //이야기를 하나 작성한다.
        Story s = newStory(mp);
        //이야기를 쓴다!
        u.writeStory(s);

        //검증과정.
        // - 공원에 이야기가 있는지 확인한다.
        //TODO  공원에 이야기가 있는지 확인하는 방법은?
        //다른 사용자로 받아온다!
        User au = newUser();
        //다른 사용자의 펫!
        Pet ap = newAnotherPet(22);
        // 역시 입양시키고
        au.adoptPet(ap);
        //이야기를 하나 작성한다.
        Story as = newStory(ap);
        //이야기를 쓴다!
        au.writeStory(as);
        //다른사용자가 기존 사용자를 볼 수 있는지 확인한다.
        List<AnothersPet> anothersPetList = au.listAnothersPets(ap.getId());
        log.debug("anothersPetList => {}", anothersPetList);
        assertThat(anothersPetList, hasItem(mp));
        assertThat(anothersPetList, not(hasItem(ap)));
        // - 내 보관함에 있는지 확인한다.
        Story myStory = u.readMyStory(s.getId());
        assertNotNull(myStory);
    }

    private Pet newAnotherPet(int seq) {
        PetId id= new PetId(seq);
        String name = RandomStringUtils.randomAlphabetic(5);
        Pet p = new NormalPet(id, name);
        beanFactory.autowireBean(p);
        return p;
    }

    private Pet newMyPet(int seq) {
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
        Story s = new NormalStory(p);
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

    @Test
    public void testListAnothersPets() throws Exception {
        //먼저 사용자를 생성해야한다.
        User u = newUser();
        //펫 생성!
        Pet mp = newMyPet(0);
        //사용자에게 입양시키구.
        u.adoptPet(mp);
        //이야기를 하나 작성한다.
        Story s = newStory(mp);
        //이야기를 쓴다!
        u.writeStory(s);

        //다른 사용자 1
        User au1 = newUser();
        //다른 사용자의 펫!
        Pet ap1 = newAnotherPet(11);
        // 역시 입양시키고
        au1.adoptPet(ap1);
        //이야기를 하나 작성한다.
        Story as1 = newStory(ap1);
        //이야기를 쓴다!
        au1.writeStory(as1);

        //다른 사용자 2
        User au2 = newUser();
        //다른 사용자의 펫!
        Pet ap2 = newAnotherPet(22);
        // 역시 입양시키고
        au2.adoptPet(ap2);
        //이야기를 하나 작성한다.
        Story as2 = newStory(ap2);
        //이야기를 쓴다!
        au2.writeStory(as2);
        
        //사용자가 다른 사용자 1,2의 이야기를 볼 수 있는지 확인한다.
        List<AnothersPet> anothersPetList = u.listAnothersPets(mp.getId());
        log.debug("anothersPetList => {}", anothersPetList);
        assertThat(anothersPetList, hasItem(ap1));
        assertThat(anothersPetList, hasItem(ap2));
        assertThat(anothersPetList, not(hasItem(mp)));
        
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