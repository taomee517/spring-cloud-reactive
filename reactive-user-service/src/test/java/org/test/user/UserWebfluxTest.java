package org.test.user;

import com.alibaba.fastjson.JSON;
import org.demo.user.UserServiceApplication;
import org.demo.user.dto.UserInfoDTO;
import org.demo.user.dto.UserSaveDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(classes = UserServiceApplication.class)
public class UserWebfluxTest {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testUserInsert() {
        UserSaveDTO userSaveDTO = new UserSaveDTO();
        userSaveDTO.setUsername("felix");
        userSaveDTO.setPassword("20171012");
        userSaveDTO.setPhoneNo("13883567715");

        webTestClient.post().uri("/user/insert")
                .body(Mono.just(userSaveDTO), UserSaveDTO.class).exchange()
                .expectStatus().isOk()
                .expectBody(Boolean.class)
                .value(result -> {
                    assertNotNull(result);
                    Assert.isTrue(result,"result should be true!");
                });
    }

    @Test
    public void testDemoRegret() {
        webTestClient.get().uri("/demo/hello?any=WebFlux").exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(result -> {
                    assertNotNull(result);
                    log.info("测试DemoController反馈结果：{}", result);
                    String expectedMsg = "Hello,WebFlux";
                    Assert.isTrue(expectedMsg.equals(result));
                });
    }

    @Test
    public void testUserGet() {
        webTestClient.get().uri("/user/info?id=6").exchange()
                .expectStatus().isOk()
                .expectBody(UserInfoDTO.class)
                .value(userInfoDTO -> {
                    log.info("测试用户查询结果：{}", JSON.toJSONString(userInfoDTO));
                    assertNotNull(userInfoDTO);
                });
    }

}
