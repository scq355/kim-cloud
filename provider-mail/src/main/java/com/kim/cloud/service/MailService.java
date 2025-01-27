package com.kim.cloud.service;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Date;

@Component
public class MailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    public void sendSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();

        //设置邮件主题
        message.setSubject("这是一封测试邮件");
        //设置邮件发送者
        message.setFrom("eqweqweqw@qq.com");
        //设置邮件接收者，可以有多个接收者
        message.setTo("rewrwer@163.com");
        //设置邮件抄送人，可以有多个抄送人
//        message.setCc("37xxxxx37@qq.com");
        //设置隐秘抄送人，可以有多个
//        message.setBcc("14xxxxx098@qq.com");
        //设置邮件发送日期
        message.setSentDate(new Date());
        //设置邮件的正文
        message.setText("这是测试邮件的正文");

        javaMailSender.send(message);
    }

    public void sendAttachFileMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("这是一封测试邮件");
        helper.setFrom("342423432@qq.com");
        helper.setTo("4234225435@163.com");
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());
        helper.setText("这是测试邮件的正文");

        //通过 addAttachment 方法来添加一个附件
        helper.addAttachment("javaboy.jpg",new File("D:\\files_scq\\pic.webp"));

        javaMailSender.send(mimeMessage);
    }

    public void sendImgResMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("这是一封测试邮件");
        helper.setFrom("342423432@qq.com");
        helper.setTo("4234225435@163.com");
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());

        //是一个 HTML 文本，里边涉及到的图片资源先用一个占位符占着，setText 方法的第二个参数 true 表示第一个参数是一个 HTML 文本
        helper.setText("<p>hello 大家好，这是一封测试邮件，这封邮件包含两种图片，分别如下</p><p>第一张图片：</p><img src='cid:p01'/><p>第二张图片：</p><img src='cid:p02'/>",true);

        //添加图片资源
        helper.addInline("p01",new FileSystemResource(new File("D:\\files_scq\\pic.webp")));
        helper.addInline("p02",new FileSystemResource(new File("D:\\files_scq\\pic.webp")));
        javaMailSender.send(mimeMessage);
    }


    public void sendThymeleafMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("这是一封测试邮件");
        helper.setFrom("342423432@qq.com");
        helper.setTo("4234225435@163.com");
//        helper.setCc("37xxxxx37@qq.com");
//        helper.setBcc("14xxxxx098@qq.com");
        helper.setSentDate(new Date());

        Context context = new Context();
        context.setVariable("username", "javaboy");
        context.setVariable("num","000001");
        context.setVariable("salary", "99999");

        String process = templateEngine.process("mail.html", context);
        helper.setText(process,true);
        javaMailSender.send(mimeMessage);

    }


}
