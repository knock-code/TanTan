package com.example.wxstc.controller;


import com.example.wxstc.entity.Essay;
import com.example.wxstc.server.EssayService;
import com.example.wxstc.utils.Response;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/school/essay")
public class EssayController {
    @Autowired
    private EssayService service;

    /*查询全部文章接口*/
    @RequestMapping(value = "/selectAllEssay", method = RequestMethod.GET)
    public Response selectAllEssay(HttpServletRequest request) {
        Cookie[] cookie = request.getCookies();
        String token = "";
        if (cookie != null) {
            for (int i = 0; i < cookie.length; i++) {
                Cookie cook = cookie[i];
                if (cook.getName().equalsIgnoreCase("token")) { //获取键
                    token = cook.getValue();
                    System.out.println("token：" + cook.getValue().toString());    //获取值
                }
            }
            if (Strings.isNotEmpty(token)) {
                List<Essay> essays = service.selectAllEssay();
                if (essays != null) {
                    essays.forEach((essay -> {
                        String year = essay.getEssay_time().substring(0,4);
                        String month = essay.getEssay_time().substring(4,6);
                        String day = essay.getEssay_time().substring(6,8);
                        String hour = essay.getEssay_time().substring(8,10);
                        String minute = essay.getEssay_time().substring(10,12);
                        String second = essay.getEssay_time().substring(12,14);
                        String date = year+"." + month + "." + day;
                        String time = hour + ":" + minute + ":" + second;
                        LocalDate now = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
                        if (date.compareTo(now.format(formatter)) >= 0){
                            essay.setEssay_time(time);
                            System.out.println("xixixi");
                        } else {
                            essay.setEssay_time(date);
                            System.out.println("hshshs");
                        }
                    }));
                    System.out.println(essays);
                    /*String json = "";
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        json = mapper.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(essays);
                    } catch (JsonProcessingException e) {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                    System.out.println(json);*/
                    //可以直接传递集合，自动转换为json格式
                    return new Response(true, "成功", 200, essays);
                } else {
                    return new Response(false, "暂无数据！", 201);
                }
            } else {
                return new Response(false, "身份验证失败！", 202);
            }
        } else {
            return new Response(false, "账号未登录！", 201);
        }
    }

    /*查询文章对应的评论和文章作者，评论作者信息*/
    @RequestMapping(value = "/selectEssayComment", method = RequestMethod.GET)
    public Response selectEssayComment(HttpServletRequest request){
        String essay_id = request.getParameter("essay_id");
        if (Strings.isNotEmpty(essay_id)){
            Response response = service.selectEssayComment(Integer.parseInt(essay_id));
            return response;
        } else {
            return new Response(false,"参数错误",201);
        }
    }
}
