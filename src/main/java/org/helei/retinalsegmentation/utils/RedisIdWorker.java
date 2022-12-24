package org.helei.retinalsegmentation.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {

     @Resource
     private StringRedisTemplate stringRedisTemplate;
    //当前时间戳
     private static final long BEGIN_TIMESTAMP = 1656374400;
     private static final int COUNT_BITS = 32;

     public long nextId(String keyPrefix){
         //生成时间戳
         LocalDateTime now = LocalDateTime.now();
         long second = now.toEpochSecond(ZoneOffset.UTC);

         long timeStamp = second-RedisIdWorker.BEGIN_TIMESTAMP;
         //生成序列号
         String format = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
         long count = stringRedisTemplate.opsForValue().increment("icr:" + keyPrefix + ":" + format);

         //拼接并返回
         return (timeStamp<<COUNT_BITS)|count;
     }

    public static void main(String[] args) {
        LocalDateTime time = LocalDateTime.of(2022, 6, 28, 0, 0, 0);
        long second = time.toEpochSecond(ZoneOffset.UTC);
        System.out.println(second);
    }
}
