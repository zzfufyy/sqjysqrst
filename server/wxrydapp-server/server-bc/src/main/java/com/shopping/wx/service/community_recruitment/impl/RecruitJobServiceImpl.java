package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IRecruitJobMapper;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitJobService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ljy
 */
@Service
public class RecruitJobServiceImpl extends CrudServiceImpl<RecruitJob> implements RecruitJobService {

    final
    IRecruitJobMapper recruitJobMapper;

    public RecruitJobServiceImpl(IRecruitJobMapper recruitJobMapper) {
        this.recruitJobMapper = recruitJobMapper;
    }

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

    /**
     * 按照距离升序进行分页查询
     *
     * @param positionPagingParam 分页参数
     * @return 结果
     */
    @Override
    public List<JobInfoDTO> pagedByDistance(PagingParam<Location> positionPagingParam) {
        startPage(positionPagingParam.getPage());
        return recruitJobMapper.pagedByDistance(positionPagingParam.getCondition());
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
