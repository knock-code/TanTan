package com.example.wxstc.serverImpl;

import com.example.wxstc.entity.User;
import com.example.wxstc.mapper.UserMapper;
import com.example.wxstc.server.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public int insert(User user) {
        try {
            int result = userMapper.register(user);
            return result;
        }
        catch(Exception e) {
            System.out.println(e.toString());
            return -1;
        }
    }

    @Override
    public List<User> select(String username) {
        List<User> userList = userMapper.login(username);
        return userList;
    }

    @Override
    public List<User> selectStudentId(String studentId) {
        List<User> userList = userMapper.selectStudentId(studentId);
        return userList;
    }

    /**
     * @param essay_id 文章id
     * */
    @Override
    public User queryUserByEssayId(Integer essay_id) {
        return userMapper.queryUserByEssayId(essay_id);
    }
}
