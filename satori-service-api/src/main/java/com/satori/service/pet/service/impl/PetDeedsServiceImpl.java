package com.satori.service.pet.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.base.utils.Bean2Utils;
import com.satori.service.model.pet.PetDeedsModel;
import com.satori.service.model.request.pet.PetDeedPageRequest;
import com.satori.service.pet.entity.PetDeeds;
import com.satori.service.pet.service.PetDeedsService;
import com.satori.service.pet.mapper.PetDeedsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yanfuyou
 * @description 针对表【pet_deeds(宠物)】的数据库操作Service实现
 * @createDate 2023-11-15 22:18:32
 */
@Service
public class PetDeedsServiceImpl extends ServiceImpl<PetDeedsMapper, PetDeeds>
        implements PetDeedsService {

    @Override
    public Page<PetDeedsModel> pageList(PetDeedPageRequest dto) {
        Page<PetDeeds> page = new Page<>(dto.getPage(), dto.getSize());
        this.baseMapper.selectPage(page, Wrappers.lambdaQuery(PetDeeds.class)
                .eq(PetDeeds::getPetId, dto.getPetId())
                .orderByDesc(PetDeeds::getOccTime));
        return Bean2Utils.copyProperties2(page, Page::new, (a, b) -> {
            Bean2Utils.copyProperties(a,b);
            List<PetDeeds> records = a.getRecords();
            List<PetDeedsModel> list = records.stream().map(r -> {
                PetDeedsModel model = new PetDeedsModel();
                Bean2Utils.copyProperties(r, model);
                String pics = r.getPics();
                List<String> pictures = JSON.parseObject(pics, new TypeReference<List<String>>() {
                });
                model.setPictures(pictures);
                return model;
            }).toList();
            b.setRecords(list);
        });
    }
}




