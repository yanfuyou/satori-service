package com.satori.service.pet.controller;

import com.satori.base.utils.Bean2Utils;
import com.satori.model.model.BaseResponse;
import com.satori.service.enums.ErrorEnum;
import com.satori.service.model.pet.PetModel;
import com.satori.service.pet.entity.PetInfo;
import com.satori.service.pet.service.PetInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author YanFuYou
 * @date 15/11/23 下午 10:25
 */

@Api(tags = "宠物相关")
@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
public class PetController {

    private final PetInfoService petInfoService;

    @ApiOperation("添加宠物")
    @PostMapping("/add")
    public BaseResponse<Object> add(@RequestBody @Validated PetModel request) {
        Long id = petInfoService.addPet(request);
        return BaseResponse.success(id);
    }

    @ApiOperation("编辑")
    @PostMapping("/edit")
    public BaseResponse<Object> edit(@RequestBody @Validated PetModel request) {
        if (null == request.getId()){
            return ErrorEnum.NULL_ID.buildResp();
        }
        PetInfo petInfo = Bean2Utils.copyProperties(request, PetInfo::new);
        petInfoService.updateById(petInfo);
        return BaseResponse.success();
    }

    @ApiOperation("状态修改")
    @PutMapping("/status/{id}/{state}")
    public BaseResponse<Object> status(@PathVariable Long id, @PathVariable Integer state) {
        petInfoService.editState(id, state);
        return BaseResponse.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/del/{id}")
    public BaseResponse<Object> del(@PathVariable Long id) {
        petInfoService.del(id);
        return BaseResponse.success();
    }

    @ApiOperation("列表")
    @GetMapping("/list/{ownerId}")
    public BaseResponse<List<PetModel>> petList(@PathVariable Long ownerId){
        List<PetModel> petModels = petInfoService.listByOwnerId(ownerId);
        return BaseResponse.success(petModels);
    }

}