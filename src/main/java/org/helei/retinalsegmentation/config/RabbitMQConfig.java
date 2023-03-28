package org.helei.retinalsegmentation.config;


import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    //交换机的名称；
    public static final String DIRECT_EXCHANGE_NAME = "segmentation_exchange";
    //队列名称；
    public static final String DIRECT_QUEUE_NAME = "segmentation_queue_1";


    /**
     * 声明交换机,在以后我们会定义多个交换机，
     * 所以给这个注入的Bean起一个名字，同理在绑定的时候用@Qualifier注解；
     * durablie：持久化
     */

    @Bean("segmentation_exchange")
    public Exchange directExchange(){
        return ExchangeBuilder.directExchange(DIRECT_EXCHANGE_NAME).durable(true).build();
    }

    //声明队列；
    @Bean("segmentation_queue_1")
    public Queue testQueue(){
        return QueueBuilder.durable(DIRECT_QUEUE_NAME).build();
    }

    //绑定交换机和队列,把上述声明的交换机、队列作为参数传入进来；
    @Bean
    public Binding bindDirectExchangeQueue(@Qualifier("segmentation_queue_1") Queue queue,
                                           @Qualifier("segmentation_exchange") Exchange exchange){

        return BindingBuilder.bind(queue).to(exchange).with("info").noargs();
    }

}
