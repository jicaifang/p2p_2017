package com.xmg.p2p.base.service.impl;

import com.xmg.p2p.base.service.IVerifyCodeService;
import com.xmg.p2p.base.util.BidConst;
import com.xmg.p2p.base.util.DateUtils;
import com.xmg.p2p.base.util.UserContext;
import com.xmg.p2p.base.vo.VerifyCodeVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * Created by seemygo on 2017/10/26.
 */
@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

    @Value("${sms.username}")
    private String username;
    @Value("${sms.msgUrl}")
    private String msgUrl;
    @Value("${sms.password}")
    private String password;
    @Value("${sms.apikey}")
    private String apikey;
    @Override
    public void sendVerifyCode(String phoneNumber){
        //校验之前是否有发短信,而且上次发生的是否>90
        VerifyCodeVo vo = UserContext.getVerifyCode();
        //1.之前没有发送过短信,可以发送
        //2.之前发送过短信,发送的时间间隔已经超过90s,可以发送
        if(vo==null || DateUtils.getBetweenTime(vo.getSendTime(),new Date())>BidConst.MESSAGE_INTERVAL_TIME){
            //1.生成验证码
            String verifyCode = UUID.randomUUID().toString().substring(0,4);
            //2.拼接发送用户文本信息
            StringBuilder message = new StringBuilder(50);
            message.append("您的验证码为:")
                    .append(verifyCode)
                    .append(",有效期为:")
                    .append(BidConst.MESSAGE_VAILD_TIME)
                    .append("分钟,请尽快使用");
            //3.发送短信
            try {
                sendMessage(phoneNumber,message.toString());
                //4.把验证码,手机号,发送时间存入session中.
                vo = new VerifyCodeVo();
                vo.setPhoneNumber(phoneNumber);
                vo.setVerifyCode(verifyCode);
                vo.setSendTime(new Date());
                UserContext.setVerifyCodeVo(vo);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

        }else{
            throw new RuntimeException("短信发送太频繁,请稍后再发");
        }
    }
    private void sendMessage(String phoneNumber,String content) throws Exception {
        //http://localhost:8080/sendMsg?username=admin&password=1111&apikey=12345&phoneNumber=1308888888&content=test
        URL url = new URL(msgUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        StringBuilder param = new StringBuilder(100);
        param.append("username=").append(username)
                .append("&password=").append(password)
                .append("&apikey=").append(apikey)
                .append("&phoneNumber=").append(phoneNumber)
                .append("&content=").append(content);
        urlConnection.getOutputStream().write(param.toString().getBytes("UTF-8"));
        urlConnection.connect();
        String responseStr = StreamUtils.copyToString(urlConnection.getInputStream(), Charset.forName("UTF-8"));
        if(!"success".equals(responseStr)){
            throw new RuntimeException("短信发送失败");
        }
        /*URL url = new URL("http://utf8.api.smschinese.cn/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        StringBuilder param = new StringBuilder(100);
        param.append("Uid=").append("lanxw01")
                .append("&Key=").append("e95741576b01edb1950d")
                .append("&smsMob=").append(phoneNumber)
                .append("&smsText=").append(content);
        urlConnection.getOutputStream().write(param.toString().getBytes("UTF-8"));
        urlConnection.connect();
        String responseStr = StreamUtils.copyToString(urlConnection.getInputStream(), Charset.forName("UTF-8"));
        int code = Integer.parseInt(responseStr);
        if(code<0){
            throw new RuntimeException("发送短信失败,请查看网关");
        }*/
    }
    @Override
    public boolean validate(String phoneNumber, String verifyCode) {
        VerifyCodeVo vo = UserContext.getVerifyCode();
        if(vo!=null &&
                vo.getPhoneNumber().equals(phoneNumber)&&//手机号码需要一致
                vo.getVerifyCode().equalsIgnoreCase(verifyCode)&&//验证码需要一致(忽略大小写)
                DateUtils.getBetweenTime(vo.getSendTime(),new Date())<=BidConst.MESSAGE_VAILD_TIME*60){//验证码在有效时间之内
            return true;
        }
        return false;
    }
}
