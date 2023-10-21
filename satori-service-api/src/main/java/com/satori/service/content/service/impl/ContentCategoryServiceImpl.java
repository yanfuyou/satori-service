package com.satori.service.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.service.content.entity.ContentCategory;
import com.satori.service.content.service.IContentCategoryService;
import com.satori.service.content.mapper.ContentCategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【content_category】的数据库操作Service实现
* @createDate 2023-09-10 17:18:58
*/
@Service
public class ContentCategoryServiceImpl extends ServiceImpl<ContentCategoryMapper, ContentCategory>
    implements IContentCategoryService {

}




