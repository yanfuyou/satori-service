package com.satori.service.content.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.base.utils.Bean2Utils;
import com.satori.service.content.entity.Content;
import com.satori.service.content.service.IContentService;
import com.satori.service.content.mapper.ContentMapper;
import com.satori.service.model.content.ContentModel;
import com.satori.service.model.request.content.ContentPageRequest;
import org.springframework.stereotype.Service;

/**
 * @author yanfuyou
 * @description 针对表【content】的数据库操作Service实现
 * @createDate 2023-09-10 17:26:37
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content>
        implements IContentService {

    @Override
    public Page<ContentModel> listPage(ContentPageRequest dto) {
        Page<Content> page = this.baseMapper.selectPage(new Page<>(dto.getPage(), dto.getSize()), Wrappers.lambdaQuery(Content.class)
                .select(Content::getId, Content::getCategoryId, Content::getCreateUserId, Content::getTitle, Content::getDeleted, Content::getCreateTime, Content::getUpdateTime)
                .eq(ObjectUtil.isNotNull(dto.getCreateUserId()), Content::getCreateUserId, dto.getCreateUserId())
                .like(StrUtil.isNotBlank(dto.getKeyWord()), Content::getTitle, dto.getKeyWord())
                .ge(ObjectUtil.isNotNull(dto.getStartTime()), Content::getCreateTime, dto.getStartTime())
                .lt(ObjectUtil.isNotNull(dto.getEndTime()), Content::getCreateTime, dto.getEndTime()));
        Page<ContentModel> modelPage = JSON.parseObject(JSON.toJSONString(page), new TypeReference<Page<ContentModel>>() {
        });
        return modelPage;
    }
}




