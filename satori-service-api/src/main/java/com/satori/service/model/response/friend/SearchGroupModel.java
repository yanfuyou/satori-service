package com.satori.service.model.response.friend;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YanFuYou
 * @date 14/10/23 下午 10:28
 */

@Data
public class SearchGroupModel implements Serializable {
    private Long id;

    private String groupName;

    private LocalDateTime createTime;

    private String avatar;

    private String desc;

    private Integer userCount;
}
