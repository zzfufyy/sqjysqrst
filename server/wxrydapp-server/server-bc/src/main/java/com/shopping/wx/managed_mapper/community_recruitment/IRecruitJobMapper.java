package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.vo.common.Location;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-25 14:45
 */

@Mapper
public interface IRecruitJobMapper {
    /**
     * 根据位置进行分页查询
     *
     * @param location 位置（经纬度）
     * @return 结果
     */
    List<JobInfoDTO> pagedByDistance(Integer salaryCompareState,Integer jobSalaryMin,Integer jobSalaryMax,@Param("location") Location location);


}
