package com.satori.service.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.service.content.entity.Content;
import com.satori.service.content.service.IContentService;
import com.satori.service.content.mapper.ContentMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【content】的数据库操作Service实现
* @createDate 2023-09-10 17:26:37
*/
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
    implements IContentService {

}




