package com.qiuxinyu.controller;

import com.qiuxinyu.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/mail")
public class MailController {
    public static final int REDIS_TIME_OUT = 30;
    @Autowired
    private JavaMailSender sender;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Value("${spring.mail.username}")
    private String from;

    @GetMapping("/send")
    public String sendEmail(@RequestParam String email) {
        String code = RandomUtil.getRandomCode(6);
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(email);
            message.setSubject("验证邮件");
            message.setText("您的验证码是：" + code + ",30分钟内有效");
            sender.send(message);
            //  将验证码存入redis，用于校验
            redisTemplate.opsForValue().set(email, code, REDIS_TIME_OUT, TimeUnit.SECONDS);
            return "success";
        } catch (MailException e) {
            return "error";
        }
    }
}
