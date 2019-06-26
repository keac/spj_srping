package com.spj.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParser;
import com.spj.demo.mapper.InfoMapper;
import com.spj.demo.mapper.LinkMapper;
import com.spj.demo.mapper.UserMapper;
import com.spj.demo.mapper.VoteMapper;
import com.spj.demo.pojo.Info;
import com.spj.demo.pojo.Link;
import com.spj.demo.pojo.User;
import com.spj.demo.pojo.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.spj.demo.security.JwtUtil.ROLE;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class Start {
    @Autowired
    private VoteMapper voteMapper;
    @Autowired
    private InfoMapper infoMapper;
    @Autowired
    private UserMapper userMapper;
    @RequestMapping("/getStInfo")
    @ResponseBody
    public String show_index(@RequestHeader(value = ROLE) String userId) {
        JSONObject result = new JSONObject();
        List<Info> lkList;
        lkList = infoMapper.selectByStu(userId);
        if (lkList.size() <= 0) {
            result.put("code", 0);
            result.put("msg", "该学生没有任何课程！");
            return result.toJSONString();
        }
        // lkList.clear();

        /* Success to result data */
        result.put("code", 1);
        result.put("msg", "ok");
        result.put("data", lkList);


        return result.toJSONString();
    }

    @RequestMapping("/sub")
    @ResponseBody
    public String sub(@RequestBody List<JSONObject> jsonParam,@RequestHeader(value = ROLE) String userId) {
        JSONObject result = new JSONObject();
        if (jsonParam.size() <= 0) {
            result.put("code", 0);
            result.put("msg", "没有任何数据！");
            return result.toJSONString();
        }
        boolean checkRes = true;
        int[] startC = new int[10];
        String msg = "信息错误！";
        for (JSONObject e : jsonParam) {
            if (Integer.parseInt(e.get("start").toString()) <= 0) {
                msg = "星星选择不能为空";
                checkRes = false;
                break;
            }
            if (e.get("reason").equals("")) {
                msg = "理由不能为空";
                checkRes = false;
                break;
            }
            startC[Integer.parseInt(e.get("start").toString())]++;
        }
        int techLe = jsonParam.size() / 2;
        for (int i : startC) {
            if (i > techLe && techLe > 1) {
                msg = "星数量不能选择超过50%";
                checkRes = false;
            }
        }
        boolean s = true;
        if (checkRes) {
            Vote vote = new Vote();
            for (JSONObject e : jsonParam) {
                String courseId= e.get("courseId").toString();
                String Reason=e.get("reason").toString();
                String StuId=e.get("stuId").toString();
                String TeachId=e.get("teachId").toString();
                vote.setCourseId(courseId);
                vote.setReason(Reason);
                vote.setStart(Integer.parseInt(e.get("start").toString()));
                vote.setStuId(StuId);
                vote.setTeachId(TeachId);
                vote.setTime(new Date());
                if(voteMapper.insert(vote)!=1){
                    s=false;
                }
            }
            if (s) {
                User user=new User();
                user.setStuId(userId);
                user.setStatus(1);
                if(userMapper.updateByUser(user)==1){
                    result.put("code", 1);
                    result.put("msg", "投票成功");
                }else{
                    result.put("code", 0);
                    result.put("msg", "更新用户状态失败");
                }

            } else {
                result.put("code", 0);
                result.put("msg", "投票失败");
            }
        } else {
            result.put("code", 0);
            result.put("msg", msg);
        }
        return result.toJSONString();
    }
}
