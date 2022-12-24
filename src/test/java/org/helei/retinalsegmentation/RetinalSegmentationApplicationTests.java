package org.helei.retinalsegmentation;

import org.helei.retinalsegmentation.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetinalSegmentationApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(PasswordEncoder.encode("123456"));
    }

}
