package org.helei.retinalsegmentation.listener;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import org.helei.retinalsegmentation.dto.MQDTO;
import org.helei.retinalsegmentation.service.IUserUploadRecordService;
import org.helei.retinalsegmentation.service.PythonService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class MyAckListener {
    @Autowired
    private PythonService pythonService;

    @Autowired
    private IUserUploadRecordService uploadRecordService;

    /**
     *
     * @param mqDTOJson 队列中的消息;
     * @param channel 当前的消息队列;
     * @param tag 取出来当前消息在队列中的的索引,
     * 用这个@Header(AmqpHeaders.DELIVERY_TAG)注解可以拿到;
     * @throws IOException
     */
    @RabbitListener(queues = "segmentation_queue_1")
    public void myAckListener(String mqDTOJson, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {

        System.out.println(mqDTOJson);
        MQDTO mqdto = JSONUtil.toBean(mqDTOJson, MQDTO.class);
        try {
            if(!mqdto.isUser()){
                pythonService.useSegmentationScript(mqdto.getSrcLct(), mqdto.getResLct());
            }else {
                long st = System.currentTimeMillis();
                pythonService.useSegmentationScript(mqdto.getSrcLct(), mqdto.getResLct());

                uploadRecordService.update()
                        .eq("id", mqdto.getRecordId())
                        .set("state", 2)
                        .set("segmentation_time", System.currentTimeMillis() - st)
                        .set("res_location", mqdto.getResLct()).update();
            }

            /**
             * 无异常就确认消息
             * basicAck(long deliveryTag, boolean multiple)
             * deliveryTag:取出来当前消息在队列中的的索引;
             * multiple:为true的话就是批量确认,如果当前deliveryTag为5,那么就会确认
             * deliveryTag为5及其以下的消息;一般设置为false
             */
            channel.basicAck(tag, false);
        }catch (Exception e){
            /**
             * 有异常就绝收消息
             * basicNack(long deliveryTag, boolean multiple, boolean requeue)
             * requeue:true为将消息重返当前消息队列,还可以重新发送给消费者;
             *         false:将消息丢弃
             */
            channel.basicNack(tag,false,true);
        }

    }

}
