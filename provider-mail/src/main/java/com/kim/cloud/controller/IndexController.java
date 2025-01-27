package com.kim.cloud.controller;

import com.kim.cloud.service.MailService;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("index")
public class IndexController {

    @Resource
    private MailService mailService;

    @GetMapping("mail/{type}")
    public String sendMail(@PathVariable String type) throws MessagingException {
        if ("1".equals(type)) {
            mailService.sendSimpleMail();
        } else if ("2".equals(type)) {
            mailService.sendAttachFileMail();
        } else if ("3".equals(type)) {
            mailService.sendImgResMail();
        } else {
            mailService.sendThymeleafMail();
        }
        return "success";
    }
}
