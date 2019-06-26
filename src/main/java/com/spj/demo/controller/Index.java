package com.spj.demo.controller;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Index {
    @RequestMapping("/")
    @ResponseBody
    public String show_index(){
        JSONObject result = new JSONObject();
        result.put("code", 400);
        result.put("msg", "Permission denied ");

        return result.toJSONString();
    }
}

