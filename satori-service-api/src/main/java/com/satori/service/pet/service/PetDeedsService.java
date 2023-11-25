package com.satori.service.pet.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.satori.service.model.pet.PetDeedsModel;
import com.satori.service.model.request.pet.PetDeedPageRequest;
import com.satori.service.pet.entity.PetDeeds;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author yanfuyou
 * @description 针对表【pet_deeds(宠物)】的数据库操作Service
 * @createDate 2023-11-15 22:18:32
 */
public interface PetDeedsService extends IService<PetDeeds> {

    Page<PetDeedsModel> pageList(PetDeedPageRequest dto);

}
