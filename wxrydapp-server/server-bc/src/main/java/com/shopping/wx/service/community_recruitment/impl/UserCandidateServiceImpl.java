package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IUserCandidateMapper;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserCandidateService;
import com.shopping.wx.util.query_utils.QueryUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 求职用户信息服务实现
 *
 * @author ljy
 * @date 2022-03-11 11:06
 */
@Service
public class UserCandidateServiceImpl extends CrudServiceImpl<UserCandidate> implements UserCandidateService {

    final
    IUserCandidateMapper userCandidateMapper;

    public UserCandidateServiceImpl(IUserCandidateMapper userCandidateMapper) {
        this.userCandidateMapper = userCandidateMapper;
    }

    @Override
    public List<UserCandidate> page(PagingParam<UserCandidateSearchCondition> pagingParam) {
        UserCandidateSearchCondition condition = pagingParam.getCondition();
        return pagingByWhereClauseBuilder(pagingParam.getPage(), builder -> builder
                // 未删除
                .notDeleted()
                // 姓名，模糊查询
                .notEmptyLike(condition.getRealName(), UserCandidate::getRealName)
                // 性别
                .notNullEq(condition.getGender(), UserCandidate::getGender)
                // 个人状态
                .notNullEq(condition.getPersonnelState(), UserCandidate::getPersonnelState)
                // 期望工作类型
                .notNullEq(condition.getExpectCategoryId(), UserCandidate::getExpectCategoryId)
                // 所在社区
                .notNullEq(condition.getCommunityUuid(), UserCandidate::getCommunityUuid)
        );
    }

    @Override
    public void increaseViewCount(String openid) {
        userCandidateMapper.increaseViewCount(openid);
    }
}
