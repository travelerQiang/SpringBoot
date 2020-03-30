package com.lzq.sharecommunity.controller;

import com.lzq.sharecommunity.entity.Composition;
import com.lzq.sharecommunity.service.CompositonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    CompositonService compositonService;

    /**
     * 查询所有作品到主页上
     * @param model
     * @return 返回首页视图
     */
    @RequestMapping("/")
    public String getComs(Model model, HttpServletRequest request){
        String pageNumStr = request.getParameter("pageNum");
        int pageNum;
        if (pageNumStr==null || pageNumStr.isEmpty()){
            pageNum=0;
        }
        else {
            pageNum=Integer.parseInt(pageNumStr)-1;
        }
        Page<Composition> allByPage = compositonService.getAllByPage(pageNum, 2);
        model.addAttribute("coms",allByPage);
        return "index";
    }
}
