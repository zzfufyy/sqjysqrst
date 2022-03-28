package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @author ljy
 */
public interface RecruitJobService extends CrudService<RecruitJob> {


    List<RecruitJob> page(PagingParam<RecruitJobSearchCondition> pagingParam);

    /**
     * 根据公司id找发布职位
     *
     * @param companyUuid
     * @param pagingParam
     * @return java.util.List<com.shopping.wx.model.RecruitJob>
     */
    List<RecruitJob> listByCompanyUuid(String companyUuid, PagingParam<RecruitJobSearchCondition> pagingParam);

}
