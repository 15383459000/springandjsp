package com.xitianfo.controller;

import com.xitianfo.entity.Image;
import com.xitianfo.form.SsmForm;
import com.xitianfo.redis.RedisRepository;
import com.xitianfo.service.ImageService;
import com.xitianfo.util.ShortMessageUtil;
import com.xitianfo.wrapper.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.schema.Model;

import java.util.List;

/**
 * @author ycSong
 * @version 1.0
 * @date 2019/12/31 18:02
 */

@Controller
@Slf4j
public class ShortMessageController {



    @Autowired
    private ShortMessageUtil shortMessageUtil;
    @Autowired
    private RedisRepository redisRepository;
    @Autowired
    private ImageService imageService;

    /**
     * 发送验证码
     * @param number 电话号
     * @return
     */
    @RequestMapping("/getMessage")
    public String getSsm(@RequestParam String number, ModelMap model) {
        String successState = "OK";
        try {
            String messageDto=shortMessageUtil.getSsm(number);
            if (successState.equals(messageDto)) {
                return "success";
            }else {
                model.addAttribute("fail",ResultWrapper.failure("错误"));
                return "fail";
            }
        } catch (Exception e) {
            return "ResultWrapper.failure(e.toString())";
        }

    }

    /**
     * 验证验证码
     * @param
     * @return
     */
    @GetMapping("/testCode")
    public String testSsm(@RequestParam String number,@RequestParam String code,ModelMap modelMap){

        //获取验证码
        String userCode = redisRepository.selectMessageCodeByPhone(number);
        //查看验证码是否相等
        if (userCode.equals(code)){
            List<Image> model=imageService.getAllImageLinks();
            modelMap.addAttribute("images",model);
            return "hello1";
        }else {
            return "testFail";
        }

    }



}



