package com.lzq.sharecommunity.controller;

import com.lzq.sharecommunity.Util.MarkdownUtils;
import com.lzq.sharecommunity.entity.Composition;
import com.lzq.sharecommunity.service.CompositonService;
import com.lzq.sharecommunity.service.UserService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

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

    /**
     *  将上传的文件保存在项目文件之外，路径为D:/workspace/upload/com
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/imageUpload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam(value = "editormd-image-file",required = true) MultipartFile file,HttpServletRequest request){
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String filename = request.getSession().getAttribute("userId")+"_"+ UUID.randomUUID().toString()+suffix;
        File newFile = new File("D:/workspace/upload/com/"+filename);
        int flag = 0;
        try {
            newFile.createNewFile();
            flag = multipartFileToFile(file,newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String,Object>();
        //回显上传之后信息
        map.put("url", "/upload/com/"+filename);
        map.put("success", flag);
        map.put("message", flag==1?"上传成功":"上传失败");
        return map;
    }

    /**
     * 完成前端传来的文件到后台文件的拷贝
     * @param source
     * @param target
     * @return
     */
    public int multipartFileToFile(MultipartFile source,File target) throws IOException {
        if (source!=null && source.getSize()>0){
            InputStream input = null;
            OutputStream output = null;
                input = source.getInputStream();
                output = new FileOutputStream(target);
                byte[] temp = new byte[2048];
                while (input.read(temp)!=-1){
                    output.write(temp);
                }
                return 1;
        }
        return 0;

    }
}
