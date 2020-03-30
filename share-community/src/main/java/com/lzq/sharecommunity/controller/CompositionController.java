package com.lzq.sharecommunity.controller;

import com.lzq.sharecommunity.Util.MarkdownUtils;
import com.lzq.sharecommunity.entity.Composition;
import com.lzq.sharecommunity.service.CompositonService;
import com.lzq.sharecommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/com")
public class CompositionController {
    @Autowired
    CompositonService compositonService;

    @Autowired
    UserService userService;

    /**
     * 保存作品
     * @param request
     * @param model
     * @return 返回到个人主页视图
     */
    @PostMapping("/save")
    public String save(HttpServletRequest request, Model model){
        String text = request.getParameter("text");
        String title = request.getParameter("title");
        String brief = request.getParameter("brief");
        Composition c = new Composition();
        int uId = Integer.parseInt(request.getSession().getAttribute("userId").toString());
        c.setUserId(uId);
        c.setContent(text);
        c.setcSize(text.length());
        c.setTitle(title);
        c.setBrief(brief);
        compositonService.addCom(c);
        return "redirect:/user/profile/"+request.getSession().getAttribute("userId");
    }

    @GetMapping("/article/{id}")
    public String dispaly(@PathVariable("id") int id, Model model){
        Composition c = compositonService.getById(id);
        c.setContent(MarkdownUtils.markdownToHtmlExtensions(c.getContent()));
        model.addAttribute("com",c);
        model.addAttribute("user",userService.findById(c.getUserId()));
        return "show-content";
    }
    @GetMapping("/article/delete/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request){
        compositonService.deleteCom(id);
        return "forward:/user/profile/"+request.getSession().getAttribute("userId");
    }
    @RequestMapping("/comment/{id}")
    public String addComment(@PathVariable("id") int id, @RequestParam("comment") String comment){

        return "show-content";
    }
}
