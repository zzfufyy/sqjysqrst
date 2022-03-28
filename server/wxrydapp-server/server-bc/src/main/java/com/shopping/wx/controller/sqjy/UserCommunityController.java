package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.UserCommunity;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_community.UserCommunityInfoVO;
import com.shopping.wx.pojo.vo.user_community.UserCommunitySerachCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.UserCommunityService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljy
 * @date 2022-03-11 16:52
 */
@RestController
@RequestMapping("/user-community")
public class UserCommunityController extends CrudController<UserCommunity, String> {

    final UserCommunityService userCommunityService;

    public UserCommunityController(UserCommunityService userCommunityService) {
        this.userCommunityService = userCommunityService;
    }

    @GetMapping("/page")
    ActionResult<PageInfo<UserCommunity>> page(PagingParam<UserCommunitySerachCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userCommunityService.page(pagingParam))
        );
    }

    @PostMapping("/add")
    ActionResult<?> add(UserCommunityInfoVO userCommunityInfoVO) {
        return insertVO(userCommunityInfoVO) ?
                ActionResult.ok() : ActionResult.error();
    }

    @GetMapping("/info")
    ActionResult<?> info(@Param("id") String userCommunityId) {
        return ActionResult.ok(
                infoVO(userCommunityId, UserCommunityInfoVO::new)
        );
    }

    @PostMapping("/modify")
    ActionResult<?> modify(UserCommunityInfoVO userCommunityInfoVO) {
        return updateVO(userCommunityInfoVO) ?
                ActionResult.ok() : ActionResult.error();
    }

    @Override
    protected CrudService<UserCommunity> getService() {
        return userCommunityService;
    }
}
