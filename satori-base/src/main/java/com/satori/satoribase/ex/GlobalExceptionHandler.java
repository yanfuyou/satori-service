package com.satori.satoribase.ex;


import com.satori.model.enums.SystemCodeEnum;
import com.satori.model.ex.BaseException;
import com.satori.model.model.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 全局异常捕获
 *
 * @author yanfuyou
 * @date 2023/09/02
 */
@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseResponse<Object> serviceException(BaseException e) {
        log.error("捕获到异常", e);
        return BaseResponse.fail("业务异常");
    }


    @ExceptionHandler(Exception.class)
    public BaseResponse<Object> SystemException(Exception e) {
        log.error("",e);
        return BaseResponse.fail(SystemCodeEnum.SYS_INTERNAL_ERR.getCode(),SystemCodeEnum.SYS_INTERNAL_ERR.getDesc());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<Object> methodArgsNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            builder.append(fieldError.getField()).append("-").append(fieldError.getDefaultMessage()).append(";");
        }
        return BaseResponse.fail(SystemCodeEnum.METHOD_ARGS_PARSING_ERR.getCode(),builder.toString());
    }
}
