package com.ocean.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://my.oschina.net/liululee/blog/2236623
 * https://www.cnblogs.com/yufeng218/p/9452621.html
 */
@RestController
@RequestMapping(value = "/mail")
public class MailController {
    private final RabbitTemplate rabbitTemplate;

//    @Autowired
    public MailController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * http://localhost:8081/mail/default
     * this.rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_MAIL_QUEUE, mailEntity);
     * 对应 {@link MailHandler#listenerAutoAck};
     * this.rabbitTemplate.convertAndSend(RabbitConfig.MANUAL_MAIL_QUEUE, mailEntity);
     * 对应 {@link MailHandler#listenerManualAck};
     */
    @GetMapping("/default")
    public void defaultMailMsg() {
        MailEntity mailEntity = new MailEntity();
        mailEntity.setId("1");
        mailEntity.setName("First Mail Message");
        mailEntity.setTitle("RabbitMQ with Spring boot!");
        mailEntity.setContent("Come on! Let's study Micro-Service together!");
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.DEFAULT_MAIL_QUEUE, mailEntity);
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.MANUAL_MAIL_QUEUE, mailEntity);
    }
}