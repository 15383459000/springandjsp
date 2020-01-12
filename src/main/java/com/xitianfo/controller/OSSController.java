package com.xitianfo.controller;

import com.aliyun.oss.OSSClient;
import com.xitianfo.service.ImageService;
import com.xitianfo.util.OSSUtil;
import com.xitianfo.wrapper.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author ycSong
 * @version 1.0
 * @date 2019/7/28 17:46
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class OSSController {

    @Autowired
    private ImageService imageService;

    private String bucketName = "yc-song";

    /**
     * 上传图片
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping ("/image")
    public ResultWrapper upload(@RequestParam("file") MultipartFile file) throws IOException {
        OSSClient ossClient = OSSUtil.getOSSClient();
        File imageFile = OSSUtil.tranFile(file);
        if (OSSUtil.isImage(imageFile)) {
            OSSUtil.uploadByFile(ossClient, imageFile, this.bucketName, "image/" + imageFile.getName());
            imageFile.delete();
            imageService.addImage("https://yc-song.oss-cn-beijing.aliyuncs.com/image/" + imageFile.getName());
            log.info("上传了一张图片，路径为：https://yc-song.oss-cn-beijing.aliyuncs.com/image/" + imageFile.getName());
            return ResultWrapper.successWithData("https://yc-song.oss-cn-beijing.aliyuncs.com/image/" + imageFile.getName());
        } else {
            imageFile.delete();
            return ResultWrapper.failure("请检查您的图片格式是否支持");
        }


    }

    /**
     * 删除图片
     * @param image
     * @return
     */
    @DeleteMapping("/image")
    public ResultWrapper delete(@RequestBody String image){

        System.out.println(image);
        imageService.deleImage(image);
        //创建oss对象
        OSSClient ossClient = OSSUtil.getOSSClient();
        OSSUtil.deleteFile(ossClient,bucketName,image);
        log.info(image+"  被删除了");
        return ResultWrapper.success("请求成功");
    }

}
