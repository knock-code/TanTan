package com.example.wxstc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Essay {
    private Integer essay_id;
    private String essay_title;
    private String essay_content;
    private String essay_time;
    private String essay_like;
    private Integer user_id;
    //一对多映射关系，主表实体含有从表实体的集合引用 文章对应评论
    private List<Comment> comments;
    public Essay() {
    }

    public Essay(String essay_title, String essay_content, String essay_time, String essay_like, Integer user_id) {
        this.essay_title = essay_title;
        this.essay_content = essay_content;
        this.essay_time = essay_time;
        this.essay_like = essay_like;
        this.user_id = user_id;
    }
}
