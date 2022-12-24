package org.helei.retinalsegmentation;

import org.helei.retinalsegmentation.converter.UserConverter;
import org.helei.retinalsegmentation.dto.UserDTO;
import org.helei.retinalsegmentation.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestConverter {
    @Test
    public void testUserConverter() {
        User user = new User();
        user.setUsername("zhanagsan");
        user.setId(123l);
        user.setIcon("http:'''");
        UserDTO userDTO = UserConverter.INSTANCE.entity2dto(user);
        System.out.println(userDTO);

        System.out.println(UserConverter.INSTANCE.dto2entity(userDTO));
    }
}
