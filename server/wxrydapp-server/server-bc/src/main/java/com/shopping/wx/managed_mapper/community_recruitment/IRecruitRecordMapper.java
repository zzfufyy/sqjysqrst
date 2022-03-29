package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.recruit_record.JobDeliveredDTO;
import com.shopping.wx.pojo.vo.common.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-25 14:45
 */

@Mapper
public interface IRecruitRecordMapper {
    /**
     * 根据 用户openid 和位置查询列表
     *
     * @param location 位置（经纬度）
     * @return 结果
     */
    List<JobDeliveredDTO> listDeliveredByCandidateOpenid(@Param("candidateOpenid") String candidateOpenid, @Param("location") Location location);

    
}
