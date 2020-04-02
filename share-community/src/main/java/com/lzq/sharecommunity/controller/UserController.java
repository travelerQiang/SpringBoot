package com.lzq.sharecommunity.controller;


import com.lzq.sharecommunity.entity.Composition;
import com.lzq.sharecommunity.entity.User;
import com.lzq.sharecommunity.service.CompositonService;
import com.lzq.sharecommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    CompositonService compositonService;
    /**
     * 获取所有用户信息
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String getUsers(Model model){
        List<User> users = userService.findAllUser();
        model.addAttribute("users",users);
        return "index";
    }

    @PostMapping("/add")
    public String addUser(User user, HttpServletRequest request){
        User save = userService.addUser(user);
        request.getSession().setAttribute("username",save.getUsername());
        request.getSession().setAttribute("userId",save.getId());
        request.getSession().setAttribute("userHead",save.getHeadUrl());
        return "redirect:/user/profile/"+save.getId();
    }

    /**
     * 验证用户名密码，并将用户名，id和头像url保存到session中
     * @param model
     * @param user
     * @param request
     * @return 返回并刷新首页视图
     */
    @PostMapping("/check")
    @ResponseBody
    public String checkUser(Model model, User user, HttpServletRequest request){
        String msg;
        if (userService.checkUser(user)){
            request.getSession().setAttribute("username",user.getUsername());
            request.getSession().setAttribute("userId",user.getId());
            request.getSession().setAttribute("userHead",user.getHeadUrl());
            msg="ok";
        }
        else msg="用户名或密码不正确";
        return msg;
    }

    /**
     * 查看指定用户id的主页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/profile/{id}")
    public String getProfileById(@PathVariable("id") int id, Model model, HttpServletRequest request){
        User one = userService.findById(id);
        one.setPassword("");
        String pageNumStr = request.getParameter("pageNum");
        int pageNum;
        if (pageNumStr==null || pageNumStr.isEmpty()){
            pageNum=0;
        }
        else {
            pageNum=Integer.parseInt(pageNumStr)-1;
        }
        Page<Composition> allByPage = compositonService.getAllByPage(pageNum, 2);
        model.addAttribute("user",one);
        model.addAttribute("coms",allByPage);
        return "user/user-index";
    }

    @GetMapping("/exit")
    public String clearInfo(HttpServletRequest request){
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("userId");
        request.getSession().removeAttribute("userHead");
        return "redirect:/";
    }

    @PostMapping("/upload/head")
    public String upload(@RequestParam("image") MultipartFile file, HttpServletRequest request){
        int userId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        String originalFilename = file.getOriginalFilename();
        String filename = userId+originalFilename.substring(originalFilename.lastIndexOf("."));      //图片后缀名不变
        File target = new File("D:/workspace/upload/user/head/"+filename);
            try {
                if (!target.exists()){
                    target.createNewFile();
                }
                BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(target));
                outputStream.write(file.getBytes());
                outputStream.flush();
                outputStream.close();
                User user = userService.findById(userId);
                user.setHeadUrl("/upload/user/head/"+filename);
                userService.update(user);
                request.getSession().setAttribute("userHead",user.getHeadUrl());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return "redirect:/user/profile/"+userId;
    }
}
