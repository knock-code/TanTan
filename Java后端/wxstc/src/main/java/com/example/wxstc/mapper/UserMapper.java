package com.example.wxstc.mapper;

import com.example.wxstc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int register(User user);
    List<User> login(String username);
    List<User> selectStudentId(String studentId);

    /*根据文章id查询作者昵称和头像*/
    User queryUserByEssayId(Integer essay_id);
    /*根据评论中的用户编号查询用户昵称和头像*/
    User queryUserByCommentUserId(Integer user_id);
}
