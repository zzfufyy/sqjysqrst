package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitJobService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.mybatis.generator.codegen.ibatis2.sqlmap.elements.ExampleWhereClauseElementGenerator;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ljy
 */
@Service
public class RecruitJobServiceImpl extends CrudServiceImpl<RecruitJob> implements RecruitJobService {

    @Override
    public List<RecruitJob> page(PagingParam<RecruitJobSearchCondition> pagingParam) {
        RecruitJobSearchCondition condition = pagingParam.getCondition();

        return pagingByWhereClauseBuilder(pagingParam.getPage(), builder -> {
                    if (condition != null) {
                        // 未删除
                        builder.notDeleted()
                                // 发布者 openid
                                .notEmptyEq(condition.getRecruiterOpenid(), RecruitJob::getRecruiterOpenid)
                                // 发布公司 uuid
                                .notEmptyEq(condition.getCompanyUuid(), RecruitJob::getCompanyUuid)
                                // 岗位 uuid
                                .notEmptyEq(condition.getCategoryUuid(), RecruitJob::getCategoryUuid);
                    }
                }
        );
    }

    @Override
    public List<RecruitJob> listByCompanyUuid(String companyUuid, PagingParam<RecruitJobSearchCondition> pagingParam) {
        RecruitJobSearchCondition condition = pagingParam.getCondition();
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<RecruitJob>()
                        .notEmptyEq(companyUuid, RecruitJob::getCompanyUuid)
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(RecruitJob::getCreateTime));
        return selectAllByExample(builder.build());
    }
}
