package com.hjy.system.dao;

import com.hjy.system.entity.SysToken;
import org.apache.ibatis.annotations.Param;

public interface TSysTokenMapper {

    SysToken findByToken(@Param("accessToken") String accessToken);

    SysToken selectByUserId(@Param("fkUserId") String fkUserId);

    int insertToken(SysToken tokenEntity);

    int updateToken(SysToken tokenEntity);

    void deleteAll();

    void deleteToken(@Param("tokenId")String tokenId);
}
