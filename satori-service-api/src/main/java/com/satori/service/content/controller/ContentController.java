package com.satori.service.content.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.satori.base.common.JacksonUtils;
import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.model.model.BaseResponse;
import com.satori.service.content.entity.Content;
import com.satori.service.content.entity.ContentCategory;
import com.satori.service.content.service.IContentService;
import com.satori.service.content.service.IContentCategoryService;
import com.satori.service.enums.ErrorEnum;
import com.satori.service.model.content.ContentCategoryModel;
import com.satori.service.model.content.ContentModel;
import com.satori.service.model.request.content.ContentPageRequest;
import com.satori.service.model.request.content.ContentRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @auth YanFuYou
 * @date 10/09/23 下午 05:30
 */

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final RedissonClient redissonClient;

    private final IContentCategoryService contentCategoryService;

    private final IContentService contentService;


    @ApiOperation("保存")
    @PostMapping("/api/content/category/save")
    public BaseResponse<Object> categorySave(@RequestBody ContentCategoryModel request) {
        if (StrUtil.isBlank(request.getName())) {
            return BaseResponse.fail(ErrorEnum.C_CATE_NULL_NAME.getCode(), ErrorEnum.C_CATE_NULL_NAME.getMsg());
        }
        ContentCategory category = new ContentCategory();
        BeanUtil.copyProperties(request, category);
        contentCategoryService.save(category);
        return BaseResponse.success();
    }

    @ApiOperation("更新")
    @PutMapping("/api/content/category/update")
    public BaseResponse<Object> cateGoryUpdate(@RequestBody ContentCategoryModel request) {
        if (ObjUtil.isNull(request.getId()) || StrUtil.isBlank(request.getName())) {
            return BaseResponse.fail(SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getCode(), SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getDesc());
        }
        ContentCategory category = new ContentCategory();
        BeanUtil.copyProperties(request, category);
        boolean b = contentCategoryService.updateById(category);

        if (b) {
            return BaseResponse.success();
        }

        return BaseResponse.fail(ErrorEnum.C_CATE_NOT_EXST.getCode(), ErrorEnum.C_CATE_NOT_EXST.getMsg());
    }


    @ApiOperation("删除类别")
    @DeleteMapping("/api/content/category/del/{id}")
    public BaseResponse<Object> categoryDel(@PathVariable("id") Long id) {
        if (ObjectUtil.isNull(id)) {
            return BaseResponse.fail(ErrorEnum.C_CATE_NULL_ID.getCode(), ErrorEnum.C_CATE_NULL_ID.getMsg());
        }
        boolean b = contentCategoryService.update(Wrappers.lambdaUpdate(ContentCategory.class).
                set(ContentCategory::getDeleted, YesOrNoEnum.YES.getValue())
                .eq(ContentCategory::getId, id));
        if (b) {
            return BaseResponse.success();
        }

        return BaseResponse.fail(ErrorEnum.C_CATE_NOT_EXST.getCode(), ErrorEnum.C_CATE_NOT_EXST.getMsg());
    }

    @ApiOperation("保存文章")
    @PostMapping("/api/content/save")
    public BaseResponse<Object> contentSave(@RequestBody @Validated ContentRequest request) {
        RLock lock = redissonClient.getLock("content.save" + request.getCreateUserId());
        if (lock.tryLock()) {
            try {
                Content content = BeanUtil.copyProperties(request, Content.class);
                contentService.save(content);
                return BaseResponse.success(BeanUtil.copyProperties(content, ContentModel.class));
            }finally {
                lock.unlock();
            }
        } else {
            return SystemCodeEnum.GET_LOCK_FAIL.buildResp();
        }
    }

    @ApiOperation("更新文章")
    @PutMapping("/api/content/update")
    public BaseResponse<ContentModel> contentUpdate(@RequestBody ContentRequest request) {
        boolean b = contentService.updateById(BeanUtil.copyProperties(request, Content.class));
        return BaseResponse.success(BeanUtil.copyProperties(request, ContentModel.class));
    }

    @ApiOperation("删除文章")
    @DeleteMapping("/api/content/del/{id}")
    public BaseResponse<Object> contentDel(@PathVariable("id") Long id) {
        contentService.update(Wrappers.lambdaUpdate(Content.class)
                .set(Content::getDeleted, YesOrNoEnum.YES)
                .eq(Content::getId, id));
        return BaseResponse.success();
    }

    @ApiOperation("获取文章列表")
    @PostMapping("/api/content/list/page")
    public BaseResponse<Page<ContentModel>> contentList(@RequestBody ContentPageRequest request) {
        Page<ContentModel> modelPage = contentService.listPage(request);
        return BaseResponse.success(modelPage);
    }

    @ApiOperation("添加评论")
    @PostMapping("/api/content/comment/save")
    public BaseResponse<Object> commentSave() {

        return BaseResponse.success();
    }

    @ApiOperation("更新评论")
    @PutMapping("/api/content/comment/update")
    public BaseResponse<Object> commentUpdate() {

        return BaseResponse.success();
    }


    @ApiOperation("删除评论")
    @DeleteMapping("/api/content/comment/del/{id}")
    public BaseResponse<Object> commentDelete() {

        return BaseResponse.success();
    }

    @ApiOperation("获取评论树")
    @GetMapping("/api/content/comment/tree/{contentId}")
    public BaseResponse<Object> commentTree() {

        return BaseResponse.success();
    }
}
