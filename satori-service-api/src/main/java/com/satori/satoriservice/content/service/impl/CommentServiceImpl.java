package com.satori.satoriservice.content.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.satoriservice.content.entity.Comment;
import com.satori.satoriservice.content.service.CommentService;
import com.satori.satoriservice.content.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【comment(评论信息表)】的数据库操作Service实现
* @createDate 2023-09-13 21:55:18
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




