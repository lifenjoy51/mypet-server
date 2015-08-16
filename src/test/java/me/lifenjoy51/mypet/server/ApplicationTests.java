package me.lifenjoy51.mypet.server;

import me.lifenjoy51.mypet.server.domain.UserType;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test to run the application.
 *
 * @author Oliver Gierke
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
// Separate profile for web tests to avoid clashing databases
public class ApplicationTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testHome() throws Exception {

        this.mvc.perform(get("/api/v1/")).andExpect(status().isOk())
                .andExpect(content().string("mypet"));
    }

    @Test
    public void 새로운_사용자_테스트() throws Exception {
        //외부에서 새로운 사용자를 등록한다.
        // 사용자는 고유번호와. 특징을 갖고있다.
        // 그밖에 필요한 정보가 있을까?
        String uuid = RandomStringUtils.randomAlphanumeric(16);
        String type = UserType.Web.toString();

        this.mvc.perform(
                post("/api/v1/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("uuid", uuid)
                .param("type", type)
        ).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.name").value("Lee"));
        
        //등록한 다음 등록번호를 받아온다?!


    }
}
