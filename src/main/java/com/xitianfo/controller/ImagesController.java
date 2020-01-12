package com.xitianfo.controller;

import com.xitianfo.entity.Image;
import com.xitianfo.service.ImageService;
import com.xitianfo.wrapper.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/image")
@Slf4j
public class ImagesController {

    @Autowired
    private ImageService imageService;

    /**
     * 获取所有图片链接
     * @return
     */
    @GetMapping("/allImage")
    public ResultWrapper getAllImages(){
        List<Image> images = imageService.getAllImageLinks();
        if (images.size()!=0&&images!=null){
            return ResultWrapper.successWithData(images);
        }else {
            return ResultWrapper.failure("没有图片");
        }
    }

}
