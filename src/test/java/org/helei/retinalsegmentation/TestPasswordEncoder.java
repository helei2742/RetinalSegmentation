package org.helei.retinalsegmentation;

import org.helei.retinalsegmentation.utils.PasswordEncoder;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestPasswordEncoder {
    @Test
    public void test(){
        System.out.println(PasswordEncoder.encode("123456"));

    }
}
