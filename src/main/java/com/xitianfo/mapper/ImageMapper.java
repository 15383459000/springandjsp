package com.xitianfo.mapper;

import com.xitianfo.entity.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ImageMapper {

    /**
     * 获取所有图片链接
     * @return 链接集合
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
