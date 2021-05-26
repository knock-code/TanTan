package com.example.wxstc.mapper;

import com.example.wxstc.entity.Essay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EssayDao {
    /*请求全部文章*/
    List<Essay> selectAllEssay();
    /*根据文章id得到文章的评论和文章*/
    Essay selectEssayComment(Integer essay_id);
    /*根据文章查询对应作者*/
    Essay queryEssayUserByEssayId();
}
