package com.example.wxstc.server;

import com.example.wxstc.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    int insert(User user); //用于注册时，添加用户
    List<User> select(String username); //用于登录时，验证用户
    List<User> selectStudentId(String studentId); //查找是否有相同学号
    User queryUserByEssayId(Integer essay_id); //根据文章id查询作者昵称头像
}
