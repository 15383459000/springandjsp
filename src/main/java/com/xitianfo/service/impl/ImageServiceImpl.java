package com.xitianfo.service.impl;

import com.xitianfo.entity.Image;
import com.xitianfo.mapper.ImageMapper;
import com.xitianfo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    /**
     * 获取所有图片
     * @return
     */
    @Override
    public List<Image> getAllImageLinks() {
        return imageMapper.getAllImageLinks();
    }

    /**
     * 添加图片
     * @param link
     */
    @Override
    public void addImage(String link) {
        imageMapper.addImage(link);
    }

    /**
     * 删除图片
     * @param link
     */
    @Override
    public void deleImage(String link) {
        System.out.println(link.length());
        imageMapper.deleImage(link);
    }
}
