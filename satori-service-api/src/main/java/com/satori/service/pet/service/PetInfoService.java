package com.satori.service.pet.service;

import com.satori.service.model.pet.PetModel;
import com.satori.service.pet.entity.PetInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author yanfuyou
* @description 针对表【pet_info(宠物)】的数据库操作Service
* @createDate 2023-11-15 22:19:12
*/
public interface PetInfoService extends IService<PetInfo> {

    Long addPet(PetModel dto);

    void editState(Long id, Integer state);

    void del(Long id);

    List<PetModel> listByOwnerId(Long ownerId);
}
