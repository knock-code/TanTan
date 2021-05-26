package com.example.wxstc.serverImpl;

import com.example.wxstc.entity.Essay;
import com.example.wxstc.entity.User;
import com.example.wxstc.mapper.EssayDao;
import com.example.wxstc.mapper.UserMapper;
import com.example.wxstc.server.EssayService;
import com.example.wxstc.utils.Response;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    private EssayDao dao;
    @Autowired
    private UserMapper mapper;

    @Override
    public List<Essay> selectAllEssay() {
        return dao.selectAllEssay();
    }

    @Override
    public Response selectEssayComment(Integer essay_id) {
        User user = mapper.queryUserByEssayId(essay_id);
        String name = user.getUser_name();
        String icon = user.getUser_icon();
        Essay essay = dao.selectEssayComment(essay_id);
        essay.getComments().forEach((comment -> {
            comment.setUser(mapper.queryUserByCommentUserId(comment.getUser_id()));
        }));
        Response response;
        if (essay != null && Strings.isNotEmpty(name)){
            response = new Response(true,"请求成功",200,name,essay);
        } else {
            response = new Response(true,"请求失败",201);
        }
        return response;
    }
}
