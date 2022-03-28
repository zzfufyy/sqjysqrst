package com.shopping.wx.managed_mapper.community_recruitment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author ljy
 * @date 2022-03-16 11:28
 */
@Mapper
public interface IUserCandidateMapper {

    /**
     * 查看次数自增
     */
    void increaseViewCount(@Param("openid") String openid);
}
