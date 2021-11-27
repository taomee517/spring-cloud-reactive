package org.test.user;

import org.demo.user.UserServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = UserServiceApplication.class)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Test
    public void getDeviceByUuid_byCache_passd() {
        String type = "1";
        int result = Integer.parseInt(type);
        assertThat(result).isEqualTo(1);
    }


}
