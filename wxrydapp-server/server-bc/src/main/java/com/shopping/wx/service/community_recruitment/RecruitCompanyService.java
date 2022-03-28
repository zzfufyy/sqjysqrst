package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @InterfaceName RecruitCompany
 * @Description 公司相关接口
 * @Author zyw
 * @Date 2022/3/11
 **/
public interface RecruitCompanyService extends CrudService<RecruitCompany> {


    /**
     * 分页 where
     *
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.model.RecruitCompany>
     */
    List<RecruitCompany> page(PagingParam<RecruitCompanySearchCondition> pagingParam);

    /**
     * 浏览量+1
     *
     * @param id
     * @return int
     */
    Boolean incrCountView(String id);

}
