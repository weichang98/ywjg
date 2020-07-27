package com.hjy.system.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * token实体类
 * @Author 大誌
 * @Date 2019/3/31 10:56
 * @Version 1.0
 */
@Data
public class SysToken implements Serializable {

//    /**
//     * tokenID
//     */
//    @Id
//    private Integer pkTokenId;
//    /**
//     * 用户ID
//     */
//    private Integer fkUserId;
    /**
     * tokenID
     */
//    @Id
    private Integer UserId;
    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
