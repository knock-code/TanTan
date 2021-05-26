package com.example.wxstc.utils;


import com.example.wxstc.entity.Essay;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Response {
    String msg;
    int code;
    Boolean isSuc = true;
    String data;
    List<Essay> essayList;
    Essay essayAndComment;
    public Response(){}
    public Response(Boolean isSuc,String msg, int code){
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
    }

    public Response(Boolean isSuc, String msg, int code, String data, Essay essayAndComment) {
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
        this.data = data;
        this.essayAndComment = essayAndComment;
    }

    public Response(Boolean isSuc, String msg, int code, String data) {
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
        this.data = data;
    }

    public Response(Boolean isSuc,String msg, int code, List<Essay> essayList) {
        this.msg = msg;
        this.code = code;
        this.isSuc = isSuc;
        this.essayList = essayList;
    }
}
