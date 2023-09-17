package com.satori.satoriservice.message.conteoller;

import com.satori.model.model.BaseResponse;
import com.satori.satoriservice.message.service.UserMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auth YanFuYou
 * @date 16/09/23 下午 11:11
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final UserMessageService userMessageService;



    @GetMapping("/api/message/onlines/get/{userId}")
    public BaseResponse<Object> getOnlines(@PathVariable Long userId){

        return BaseResponse.success();
    }
}
