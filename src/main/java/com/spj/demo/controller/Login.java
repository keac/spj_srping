package com.spj.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.spj.demo.mapper.StuMapper;
import com.spj.demo.mapper.UserMapper;
import com.spj.demo.pojo.User;
import com.spj.demo.security.JwtUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootApplication
@RestController
@RequestMapping("/login")
public class Login {
    public boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    @RequestMapping("/")
    @ResponseBody
    public String login() {
        return "No!";
    }

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StuMapper stuMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private String openid = "";
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @RequestMapping("/is_login")
    @ResponseBody
    public String is_login(HttpServletRequest request, String code) throws IOException {

        JSONObject result = new JSONObject();
        if (code != null) {
            String getOpenIdUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
            String appid = "****";
            String secret = "****";
            String requestUrl = getOpenIdUrl.replace("JSCODE", code);
            requestUrl = requestUrl.replace("APPID", appid);
            requestUrl = requestUrl.replace("SECRET", secret);
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            //向微信发送请求并获取response
            String response = httpClient.execute(httpGet, responseHandler);
            JSONObject jsonObject = JSON.parseObject(response);
            openid = jsonObject.get("openid").toString();


        } else {
            result.put("code", 400);
            result.put("msg", "No Code.");
        }

        if (!openid.equals("")){


            User userL=userMapper.selectByPrimaryKey(openid);
            if ( userL!= null) {
                int status=userL.getStatus();
                if(status==1){
                    result.put("code", 2);
                    result.put("msg", "已投过票");
                    return result.toJSONString();
                }
                if(status==4){
                    result.put("code", 4);
                    result.put("msg", "该账号已被禁用");
                    return result.toJSONString();
                }

                User userT=userMapper.selectByPrimaryKey(openid);
                String jwt = JwtUtil.generateToken(userT.getStuId());

                result.put("code", 1);
                result.put("token", jwt);
                result.put("data", userT.getStuId());
                result.put("msg", "您已经绑定账号，将自动为您登陆");

            } else {
                result.put("code", 0);
                result.put("msg", "您还没有绑定账号");
            }
        }


        return result.toJSONString();

    }

    @RequestMapping("/to_login")
    @ResponseBody
    public String to_login(String userId, String userPhone, String checkNum) {
        JSONObject result = new JSONObject();
        if (openid.equals("")) {
            result.put("code", 0);
            result.put("msg", "没有openid 请退出程序重试");
            return result.toJSONString();
        }
        User userT=userMapper.selectByPrimaryKey(openid);
        if (userT != null) {
            result.put("code", 0);
            result.put("msg", "您的id已绑定学号 "+userT.getStuId());
            return result.toJSONString();
        }
        if (userId.equals("")) {
            result.put("code", 0);
            result.put("msg", "学号/教工号为空");
            return result.toJSONString();
        }
        if (userMapper.selectByUserId(userId) != null) {
            result.put("code", 0);
            result.put("msg", "学号已被绑定");
            return result.toJSONString();
        }
        if (stuMapper.selectByPrimaryKey(userId) == null) {
            result.put("code", 0);
            result.put("msg", "学号不存在");
            return result.toJSONString();
        }
        if (!isMobile(userPhone)) {
            result.put("code", 0);
            result.put("msg", "请输入正确的手机号码");
            return result.toJSONString();
        }
        if (userMapper.selectByPhone(userPhone) != null) {
            result.put("code", 0);
            result.put("msg", "手机号已被绑定");
            return result.toJSONString();
        }

        if (checkNum.equals("")) {
            result.put("code", 0);
            result.put("msg", "请输入验证码");
            return result.toJSONString();
        }
        if (stringRedisTemplate.opsForValue().get(userPhone) != null) {
            String u_code = stringRedisTemplate.opsForValue().get(userPhone);
            JSONObject uCodeObject = JSON.parseObject(u_code);
            if (!(checkNum.equals(uCodeObject.get("code").toString()))) {
                result.put("code", 0);
                result.put("msg", "验证码错误");
                return result.toJSONString();
            }
            if (!(userPhone.equals(uCodeObject.get("userPhone").toString()))) {
                result.put("code", 0);
                result.put("msg", "手机号码不匹配，请勿修改手机号");
                return result.toJSONString();
            }
        }else{
            result.put("code", 0);
            result.put("msg", "手机号码不匹配，请勿修改手机号");
            return result.toJSONString();
        }
        User user=new User();
        user.setStuId(userId);
        user.setOpenid(openid);
        user.setPhone(userPhone);
        user.setLogTime(new Date());
        user.setStatus(0);
       if(userMapper.insert(user)==1){


        String jwt = JwtUtil.generateToken(userId);
        System.out.println(jwt);
        result.put("code", 1);
        result.put("token", jwt);
        result.put("msg", "绑定成功");
       }else{
           result.put("code", 0);
           result.put("msg", "绑定失败");
       }

        return result.toJSONString();
    }


}
