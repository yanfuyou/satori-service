package com.satori.service.user.controller;

import com.satori.base.common.Formaters;
import com.satori.base.utils.Bean2Utils;
import com.satori.model.model.BaseResponse;
import com.satori.service.model.UserTodoModel;
import com.satori.service.user.entity.UserTodo;
import com.satori.service.user.service.UserTodoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;

/**
 * @author YanFuYou
 * @date 05/11/23 下午 09:41
 */
@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {
    private final UserTodoService userTodoService;

    @ApiOperation("保存")
    @PostMapping("/save")
    public BaseResponse<Object> save(@RequestBody @Validated UserTodoModel request) {
        userTodoService.save(Bean2Utils.clone(request, UserTodo.class));
        return BaseResponse.success();
    }

    @ApiOperation("更新状态")
    @GetMapping("/change_state")
    public BaseResponse<Object> changeState(@RequestParam Long id, @RequestParam Integer state) {
        userTodoService.changeState(id, state);
        return BaseResponse.success();
    }

    @ApiOperation("列表")
    @GetMapping("/list")
    public BaseResponse<List<UserTodoModel>> list(@RequestParam Long userId, @RequestParam String createTime){
        LocalDateTime parse = LocalDateTime.parse(createTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        List<UserTodoModel> userTodoModels = userTodoService.todoList(userId, parse);
        return BaseResponse.success(userTodoModels);
    }
}
