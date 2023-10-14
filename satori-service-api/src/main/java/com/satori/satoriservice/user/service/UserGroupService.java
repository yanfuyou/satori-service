package com.satori.satoriservice.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.satoribase.utils.Bean2Utils;
import com.satori.satoriservice.model.request.user.PageFriendRequest;
import com.satori.satoriservice.model.response.friend.SearchGroupModel;
import com.satori.satoriservice.user.entity.UserGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Objects;

/**
 * @author yanfuyou
 * @description 针对表【user_group(用户组)】的数据库操作Service
 * @createDate 2023-09-16 22:49:06
 */
public interface UserGroupService extends IService<UserGroup> {

    default Page<SearchGroupModel> pageList(PageFriendRequest dto) {
        Page<UserGroup> page = new Page<>(dto.getPage(), dto.getSize(), false);
        getBaseMapper().selectPage(page, Wrappers.lambdaQuery(UserGroup.class)
                .eq(UserGroup::getDeleted, YesOrNoEnum.NO.getValue())
                .like(Objects.nonNull(dto.getKeyword()), UserGroup::getGroupName, dto.getKeyword()));
        List<SearchGroupModel> models = page.getRecords().stream().map(g -> {
            SearchGroupModel model = new SearchGroupModel();
            model.setId(g.getId());
            model.setGroupName(g.getGroupName());
            model.setAvatar("");
            model.setCreateTime(g.getCreateTime());
            model.setDesc(g.getDescription());
            model.setUserCount(0);
            return model;
        }).toList();
        page.getRecords().clear();
        Page<SearchGroupModel> clone = Bean2Utils.clone(page, Page.class);
        clone.setRecords(models);
        return clone;
    }
}
