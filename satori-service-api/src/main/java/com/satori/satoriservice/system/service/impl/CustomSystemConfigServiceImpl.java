package com.satori.satoriservice.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.satori.satoriservice.system.entity.CustomSystemConfig;
import com.satori.satoriservice.system.service.CustomSystemConfigService;
import com.satori.satoriservice.system.mapper.CustomSystemConfigMapper;
import org.springframework.stereotype.Service;

/**
* @author yanfuyou
* @description 针对表【custom_system_config】的数据库操作Service实现
* @createDate 2023-09-30 00:05:01
*/
@Service
public class CustomSystemConfigServiceImpl extends ServiceImpl<CustomSystemConfigMapper, CustomSystemConfig>
    implements CustomSystemConfigService{

}




