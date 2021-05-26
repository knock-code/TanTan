package com.example.wxstc;

import com.example.wxstc.entity.Essay;
import com.example.wxstc.entity.User;
import com.example.wxstc.mapper.EssayDao;
import com.example.wxstc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootTest
class WxstcApplicationTests {
    @Autowired
    private EssayDao dao;
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        String bing = "2021.05.20";
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        System.out.println(date.format(formatter));
        if (bing.compareTo(date.format(formatter)) >= 0){
            System.out.println("xixixi");
        } else {
            System.out.println("hshshs");
        }
    }

    @Test
    void essayAndComment(){
        Essay essay = dao.selectEssayComment(1);
        System.out.println(essay);
    }

    @Test
    void queryUserByEssayId(){
        User user = userMapper.queryUserByEssayId(1);
        System.out.println(user);
    }
    /*测试查询写评论的人*/
    @Test
    void queryUserByCommentUserId(){
        User user = userMapper.queryUserByCommentUserId(1);
        System.out.println(user);
    }
}
