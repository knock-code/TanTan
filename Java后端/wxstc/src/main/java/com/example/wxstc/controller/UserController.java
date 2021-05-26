package com.example.wxstc.controller;

import com.example.wxstc.utils.Response;
import com.example.wxstc.entity.User;
import com.example.wxstc.server.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/school")
public class UserController {
    @Resource
    private UserService userService;

    /*
    * 注册接口
    * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestParam Map<String, String> person) {
        String username = person.get("username");
        String icon = person.get("icon");
        String password = person.get("password");
        String studentId = person.get("studentId");
        //1.判断用户名、密码、手机号是否为空
        if(Strings.isNotEmpty(username) && Strings.isNotEmpty(password) && Strings.isNotEmpty(studentId)){
            List<User> users =  userService.select(username);
            //2.判断是否有重复用户名
            if(users!=null && users.size()>0){
                return new Response(true,"注册失败，用户名重复,请更换",-1);
            }else {
                //再次查询学号有木有重复
                List<User> users1 =  userService.selectStudentId(studentId);
                if (users1!=null && users1.size()>0) {
                    return new Response(true,"注册失败，学号重复，请重新输入！",-1);
                } else {
                    User user = new User(username,icon,password,studentId);
                    System.out.println(user);
                    int t = userService.insert(user);
                    if(t == 1){
                        return new Response(true,"注册成功",1);
                    }else {
                        return new Response(true,"注册失败",-1);
                    }
                }
            }
        }else{
            return new Response(true,"注册失败，请检查用户名、密码、手机号是否为空",-1);
        }
    }

    /*
    * 登录接口
    * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response login(@RequestParam Map<String, String> person,
                          HttpServletRequest request, HttpServletResponse response){
        String username = person.get("username");
        String password =person.get("password");
        //1. 判断用户名、密码是否为空
        if(Strings.isNotEmpty(username) && password != null ){
            List<User> users =  userService.select(username);
            //2. 判断用户名是否存在
            if(users!=null && users.size()>0){
                User user = users.get(0);
                System.out.println(user);
                //3. 判断密码是否正确
                if(password.equals(user.getUser_password())){
                    //设置添加session
                    //request.getSession(true).setAttribute("userName",user.getUser_name());
                    //设置添加Cookie和token
                    Cookie cookie = new Cookie("token", String.valueOf(user.getUser_id()));
                    cookie.setMaxAge(60);
                    response.addCookie(cookie);
                    //4. 密码正确，登陆成功
                    return new Response(true,"登陆成功",1);
                }else {
                    return new Response(false,"密码错误",-1);
                }
            }else {
                return new Response(true,"用户名不存在",-1);
            }
        }else {
            return new Response(true,"登陆失败，请检查用户名、密码是否为空",-1);
        }
    }

    /*退出注销账号接口*/
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout(HttpServletRequest request){
        if (request.getSession(true).getAttribute("userName") != null
            && !"".equals(request.getSession(true).getAttribute("userName"))) {
            //移出Session
            request.getSession(true).removeAttribute("userName");
            return "/login";
        } else {
            return "请登录账号！";
        }
    }

    /*根据文章id查询作者的昵称和头像*/
    @RequestMapping(value = "/queryUserByEssayId", method = RequestMethod.GET)
    public Response queryUserByEssayId(HttpServletRequest request){
        String essay_id = request.getParameter("essay_id");
        if (Strings.isNotEmpty(essay_id)){
            User user = userService.queryUserByEssayId(Integer.parseInt(essay_id));
            String name = user.getUser_name();
            String icon = user.getUser_icon();
            return new Response(true,"获取成功",200,name);
        } else {
            return new Response(false,"访问失败",201);
        }
    }
}
