package com.satori.service.model.request.pet;

import com.satori.model.model.BasePageRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YanFuYou
 * @date 24/11/23 下午 08:55
 */

@EqualsAndHashCode(callSuper = false)
@Data
public class PetDeedPageRequest extends BasePageRequest {


    @NotNull(message = "宠物id不能为空")
    private Long petId;
}
