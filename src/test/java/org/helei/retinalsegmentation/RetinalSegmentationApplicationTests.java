package org.helei.retinalsegmentation;

import org.helei.retinalsegmentation.utils.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class RetinalSegmentationApplicationTests {

    @Test
    void contextLoads() {

        System.out.println(PasswordEncoder.encode("123456"));
        String path = "D:\\ideaworkspace\\retinalsegmentation\\retinalsegmentation\\images\\user\\heleidage666\\srcImages\\Src_0581_Image_01L.jpg";
        System.out.println(path);
        System.out.println(Arrays.toString(path.split("\\\\")));
    }

}
