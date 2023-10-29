package com.satori.service.content.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.satori.service.content.entity.Content;
import com.baomidou.mybatisplus.extension.service.IService;
import com.satori.service.model.content.ContentModel;
import com.satori.service.model.request.content.ContentPageRequest;

/**
* @author yanfuyou
* @description 针对表【content】的数据库操作Service
* @createDate 2023-09-10 17:26:37
*/
public interface IContentService extends IService<Content> {

    Page<ContentModel> listPage(ContentPageRequest dto);

}
