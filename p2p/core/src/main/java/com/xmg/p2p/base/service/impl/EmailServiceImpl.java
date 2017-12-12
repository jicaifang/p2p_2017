package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.domain.MailVerify;
import com.xmg.p2p.base.domain.Userinfo;
import com.xmg.p2p.base.service.IEmailService;
import com.xmg.p2p.base.service.IMailVerifyService;
import com.xmg.p2p.base.service.IUserinfoService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.UUID;

/**
 * Created by seemygo on 2017/10/27.
 */
@Service
public class EmailServiceImpl implements IEmailService {
    //http://localhost:8080/bindEmail?key=
    @Value("${email.applicationBindEmailUrl}")
    private String applicationBindEmailUrl;
    @Autowired
    private IUserinfoService userinfoService;
    @Autowired
    private IMailVerifyService mailVerifyService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendEmail(String email) {
        //1.创建UUID字符串
        String uuid = UUID.randomUUID().toString();
        //2.拼接邮件的内容(html)
        StringBuilder message = new StringBuilder(100);
        // http://localhost:8080/bindEmail?key=xxxxx
        message.append("感谢注册P2P网站,请完成认证.点击<a href='")
                .append(applicationBindEmailUrl)
                .append(uuid)
                .append("'>这里</a>完成认证,邮件有效期为")
                .append(BidConst.EMAIL_VAILD_DAY)
                .append("天,请尽快认证.");
        //3.执行邮件发送
        try {
            sendRealEmail(email,message.toString());
            //4.需要把当前用户id,发送的邮箱,发送时间,uuid存储到数据库中.
            MailVerify mailVerify = new MailVerify();
            mailVerify.setUserinfoId(UserContext.getCurrent().getId());
            mailVerify.setEmail(email);
            mailVerify.setSendTime(new Date());
            mailVerify.setUuid(uuid);
            mailVerifyService.save(mailVerify);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    private void sendRealEmail(String email,String content) throws Exception {
        //1.创建message消息体
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //2.创建helper
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        //3.配置收件人
        helper.setTo(email);
        helper.setFrom("xmgadmin@126.com");
        //4.设置邮件的标题
        helper.setSubject("这是一份认证邮件");
        //5.设置内容
        helper.setText(content,true);
        //6.发送邮件
        javaMailSender.send(mimeMessage);
    }
}
