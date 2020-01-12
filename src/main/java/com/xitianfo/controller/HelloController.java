package com.xitianfo.controller;

import com.xitianfo.entity.Image;
import com.xitianfo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.schema.Model;

import java.util.List;

/**
 * @Author ycSong
 * @create 2020/1/2 15:31
 */
@Controller
public class HelloController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/hello")
    public String Hello(ModelMap modelMap){
        List<Image> model=imageService.getAllImageLinks();
        modelMap.addAttribute("images",model);
        return "hello1";
    }
    @RequestMapping("/xitian")
    public String hello1(){
        return "hello";
    }
}
