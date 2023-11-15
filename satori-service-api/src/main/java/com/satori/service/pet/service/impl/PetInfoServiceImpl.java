package com.satori.service.pet.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.service.pet.entity.PetInfo;
import com.satori.service.pet.service.PetInfoService;
import com.satori.service.pet.mapper.PetInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【pet_info(宠物)】的数据库操作Service实现
* @createDate 2023-11-15 22:19:12
*/
@Service
public class PetInfoServiceImpl extends ServiceImpl<PetInfoMapper, PetInfo>
    implements PetInfoService{

}




