package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IRecruitRecordMapper;
import com.shopping.wx.model.RecruitRecord;
import com.shopping.wx.pojo.dto.recruit_record.RecordForCandidateDTO;
import com.shopping.wx.pojo.dto.recruit_record.RecordForRecruiterDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.recruit_record.RecruitRecordSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ljy
 * @date 2022-03-16 15:57
 */

@Service
public class RecruitRecordServiceImpl extends CrudServiceImpl<RecruitRecord> implements RecruitRecordService {
    @Autowired
    IRecruitRecordMapper iRecruitRecordMapper;

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

    @Override
    public List<RecordForCandidateDTO> listRecordPlusByCandidateOpenid(String candidateOpenid, Location location) {
        return iRecruitRecordMapper.listRecordPlusByCandidateOpenid(candidateOpenid, location);
    }

    @Override
    public List<RecordForRecruiterDTO> listRecordPlusByCompanyUuid(String companyUuid) {
        return iRecruitRecordMapper.listRecordPlusByCompanyUuid(companyUuid);
    }
}
