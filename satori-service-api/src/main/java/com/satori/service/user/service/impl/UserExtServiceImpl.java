package com.satori.service.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.base.utils.Bean2Utils;
import com.satori.service.model.UserExtModel;
import com.satori.service.user.entity.UserExt;
import com.satori.service.user.service.UserExtService;
import com.satori.service.user.mapper.UserExtMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yanfuyou
 * @description 针对表【user_ext(用户扩展信息)】的数据库操作Service实现
 * @createDate 2023-11-05 13:36:35
 */
@Service
public class UserExtServiceImpl extends ServiceImpl<UserExtMapper, UserExt>
        implements UserExtService {
    @Override
    public void saveUserExt(UserExtModel dto) {
        if (null == dto.getId()) {
            //新增
            this.baseMapper.insert(Bean2Utils.clone(dto, UserExt.class));
        } else {
            //更新
            this.baseMapper.updateById(Bean2Utils.clone(dto, UserExt.class));
        }
    }

    @Override
    public UserExtModel userExtGet(Long id) {
        return Optional.ofNullable(this.baseMapper.selectOne(Wrappers.lambdaQuery(UserExt.class)
                        .eq(UserExt::getUserId, id)))
                .map(ext -> Bean2Utils.clone(ext, UserExtModel.class))
                .orElseGet(UserExtModel::new);
    }
}




