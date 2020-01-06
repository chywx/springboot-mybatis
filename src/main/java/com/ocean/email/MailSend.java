package com.ocean.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailSend {

    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/testsend.do")
    @ResponseBody
    public String sendSimpleMail() throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("1559843332@qq.com");
        message.setTo("chy1559843332@163.com");
        message.setSubject("主题：简单邮件");
        message.setText("大海测试邮件内容");
        mailSender.send(message);
        return "发送成功";
    }


}