package com.xitianfo.service;

import com.xitianfo.entity.Image;

import java.util.List;

public interface ImageService {

    /**
     * 获取所有图片的链接
     * @return
     */
    List<Image> getAllImageLinks();

    /**
     * 添加新图片
     * @param link
     */
    void addImage(String link);

    /**
     * 删除图片
     * @param link
     */
    void deleImage(String link);
}
