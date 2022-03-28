package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.model.RecruitRecord;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_record.RecruitRecordSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitRecordService;
import com.shopping.wx.util.query_utils.Paging;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import com.shopping.wx.util.query_utils.impl.CustomPaging;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-16 15:57
 */

@Service
public class RecruitRecordServiceImpl extends CrudServiceImpl<RecruitRecord> implements RecruitRecordService {

    @Override
    public List<RecruitRecord> page(PagingParam<RecruitRecordSearchCondition> pagingParam) {
        RecruitRecordSearchCondition condition = pagingParam.getCondition();
        return pagingByWhereClauseBuilder(pagingParam.getPage(), builder -> builder
                // 未删除
                .notDeleted()
                // 时间范围限制
                .dateRange(condition, RecruitRecord::getCreateTime)
                // 候选人 openid
                .notEmptyEq(condition.getCandidateOpenid(), RecruitRecord::getCandidateOpenid)
                // 招聘人 openid
                .notEmptyEq(condition.getRecruiterOpenid(), RecruitRecord::getRecruiterOpenid)
                // 查看状态
                .notNullEq(condition.getFlagViewReceive(), RecruitRecord::getFlagViewReceive)
        );
    }
}
