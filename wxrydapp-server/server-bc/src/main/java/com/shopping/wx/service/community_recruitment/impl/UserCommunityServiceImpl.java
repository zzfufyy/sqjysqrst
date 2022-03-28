package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.UserCommunity;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_community.UserCommunitySerachCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.UserCommunityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社区管理人员用户服务实现类
 *
 * @author ljy
 * @date 2022-03-11 16:46
 */

@Service
public class UserCommunityServiceImpl extends CrudServiceImpl<UserCommunity> implements UserCommunityService {

    // TODO: 实现具体的分页逻辑
    @Override
    public List<UserCommunity> page(PagingParam<UserCommunitySerachCondition> pagingParam) {
        return null;
    }
}
