package com.spj.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@RestController
@RequestMapping("/msg")
public class Msg {
    public  boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/send")
    @ResponseBody
    public String msg(HttpServletRequest request,String userPhone){
        JSONObject result = new JSONObject();
        System.out.println(userPhone);
        System.out.println(isMobile(userPhone));
        if(isMobile(userPhone)){
            int number = (int) (Math.random() * 899999) + 100000;
            System.out.println(number);
            JSONObject msg = new JSONObject();
            msg.put("userPhone", userPhone);
            msg.put("code", number);
            msg.put("openid", request.getSession().getAttribute("openid"));
            stringRedisTemplate.opsForValue().set(userPhone, msg.toJSONString());
            result.put("c", number);
            result.put("msg", "send ok");
            result.put("code", 1);
        }else{
            result.put("msg", "手机号错误");
            result.put("code", 0);
        }
        return result.toJSONString();
    }
}
