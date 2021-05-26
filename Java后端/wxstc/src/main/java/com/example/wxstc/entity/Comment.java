package com.example.wxstc.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 高辉
 * @date 2021.05.20
 * 评论实体类
 */
@Getter
@Setter
@ToString
public class Comment {
    private Integer comment_id;
    private String comment_content;
    private String comment_time;
    private Integer comment_like;
    private Integer user_id;
    private Integer essay_id;
    private User user;

    public Comment() {
    }

    public Comment(String comment_content, String comment_time, Integer comment_like, Integer user_id, Integer essay_id) {
        this.comment_id = comment_id;
        this.comment_content = comment_content;
        this.comment_time = comment_time;
        this.comment_like = comment_like;
        this.user_id = user_id;
        this.essay_id = essay_id;
    }
}
