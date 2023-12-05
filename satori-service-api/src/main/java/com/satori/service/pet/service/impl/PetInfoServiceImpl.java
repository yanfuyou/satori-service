package com.satori.service.pet.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.druid.wall.violation.ErrorCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.base.utils.Bean2Utils;
import com.satori.model.enums.YesOrNoEnum;
import com.satori.service.enums.ErrorEnum;
import com.satori.service.enums.SystemConfigNameEnum;
import com.satori.service.model.pet.PetModel;
import com.satori.service.pet.entity.PetInfo;
import com.satori.service.pet.service.PetInfoService;
import com.satori.service.pet.mapper.PetInfoMapper;
import com.satori.service.system.entity.CustomSystemConfig;
import com.satori.service.system.service.CustomSystemConfigService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yanfuyou
 * @description 针对表【pet_info(宠物)】的数据库操作Service实现
 * @createDate 2023-11-15 22:19:12
 */
@Service
@RequiredArgsConstructor
public class PetInfoServiceImpl extends ServiceImpl<PetInfoMapper, PetInfo>
        implements PetInfoService {

    private final CustomSystemConfigService customSystemConfigService;

    private final RedissonClient redissonClient;

    private final PetInfoMapper petInfoMapper;
    @Override
    public Long addPet(PetModel dto) {
        RMap<Object, Object> limit = redissonClient.getMap(SystemConfigNameEnum.LIMIT.name);
        Integer maxPets = (Integer) limit.get(SystemConfigNameEnum.MAX_PETS.name);
        if (ObjectUtil.isNull(maxPets)) {
            CustomSystemConfig config = customSystemConfigService.getOne(Wrappers.lambdaQuery(CustomSystemConfig.class)
                    .eq(CustomSystemConfig::getParamName, SystemConfigNameEnum.MAX_PETS.name));
            maxPets = Integer.parseInt(config.getParamValue());
        }
        Long count = this.baseMapper.selectCount(Wrappers.lambdaQuery(PetInfo.class)
                .eq(PetInfo::getOwnerId, dto.getOwnerId())
                .eq(PetInfo::getDeleted, YesOrNoEnum.NO));

        if (count >= maxPets) {
            throw ErrorEnum.P_MAX_LIMIT.buildEx();
        }
        PetInfo petInfo = Bean2Utils.copyProperties(dto, PetInfo::new);
        this.baseMapper.insert(petInfo);
        return petInfo.getId();
    }

    @Override
    public void editState(Long id, Integer state) {
        this.baseMapper.update(null, Wrappers.lambdaUpdate(PetInfo.class)
                .set(PetInfo::getState, state)
                .eq(PetInfo::getId, id));
    }

    @Override
    public void del(Long id) {
        this.baseMapper.update(null, Wrappers.lambdaUpdate(PetInfo.class)
                .set(PetInfo::getDeleted, YesOrNoEnum.YES)
                .eq(PetInfo::getId, id));
    }

    @Override
    public List<PetModel> listByOwnerId(Long ownerId) {
        List<PetInfo> petInfos = this.baseMapper.selectList(Wrappers.lambdaQuery(PetInfo.class)
                .eq(PetInfo::getDeleted, YesOrNoEnum.NO)
                .eq(PetInfo::getOwnerId, ownerId)
                .orderBy(true, true, PetInfo::getCreateTime));
        return Bean2Utils.copyProperties(petInfos, PetModel::new);
    }

    @Override
    public List<PetModel> random(Integer num) {
        return petInfoMapper.randomList(num);
    }
}




