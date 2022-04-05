package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * 求职用户信息服务
 *
 * @author ljy
 * @date 2022-03-11 16:45
 */
public interface UserCandidateService extends CrudService<UserCandidate> {


    List<UserCandidate> page(PagingParam<UserCandidateSearchCondition> pagingParam);

    /**
     * 有用户查看此用户的信息或简历
     * TODO: 实现查看表保存数据，而非简单地记录次数，可以根据查看表获得信息
     * <p>
     * {@code
     * void view(String viewerOpenid,String openid)
     * }
     */
    void increaseViewCount(String openid);


    List<UserCandidateDTO> pagedByDistance(Integer jobSalaryMin, Integer jobSalaryMax, PagingParam<Location> positionPagingParam);


}
