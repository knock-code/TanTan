package com.example.wxstc.server;

import com.example.wxstc.entity.Essay;
import com.example.wxstc.utils.Response;

import java.util.List;

public interface EssayService {
    List<Essay> selectAllEssay();
    Response selectEssayComment(Integer essay_id);
}
