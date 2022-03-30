package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.vo.common.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<UserCandidateDTO> pagedByDistance(@Param("location") Location location);
}
