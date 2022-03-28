package com.shopping.wx.managed_mapper.community_recruitment;

import com.shopping.wx.model.RecruitCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @InterfaceName CustomRecruitmentCompanyMapper
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/16
 **/
@Mapper
public interface IRecruitCompanyMapper extends tk.mybatis.mapper.common.Mapper<RecruitCompany> {
    /**
    * countView ++
    * @param  id
    * @return int
    */
    int incrCountView(@Param(value="id") String id);
}
