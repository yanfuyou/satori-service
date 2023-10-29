package com.satori.model.enums;

import com.satori.model.ex.BaseException;
import com.satori.model.model.BaseResponse;

/**
 * @author YanFuYou
 * @date 29/10/23 下午 02:54
 */
public interface ICodeService {

    BaseException buildEx();
    BaseException buildEx(Object... args);

    BaseResponse<Object> buildResp();
}
