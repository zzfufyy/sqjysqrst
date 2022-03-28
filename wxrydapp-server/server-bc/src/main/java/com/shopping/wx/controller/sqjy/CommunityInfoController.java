package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CommunityInfoService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 社区信息接口
 *
 * @author ljy
 */

@RequestMapping("/community-info")
@RestController()
public class CommunityInfoController extends CrudController<CommunityInformation, String> {

    final
    CommunityInfoService communityInfoService;

    public CommunityInfoController(CommunityInfoService communityInfoService) {
        this.communityInfoService = communityInfoService;
    }

    @RequestMapping("/page")
    ActionResult<PageInfo<CommunityInformation>> page(
            @RequestBody PagingParam<CommunityInfoSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(communityInfoService.page(pagingParam))
        );
    }

    @RequestMapping("/add")
    ActionResult<?> add(@RequestBody CommunityInformation communityInformation) {
        insertVO(communityInformation);
        return ActionResult.ok();
    }

    @RequestMapping("/info")
    public ActionResult<CommunityInformation> info(@Param("id") String communityInformationId) {
        return ActionResult.ok(query(communityInformationId));
    }

    @RequestMapping("/modify")
    ActionResult<?> modify(@RequestBody CommunityInformation communityInformation) {
        update(communityInformation);
        return ActionResult.ok();
    }

    @RequestMapping("/list")
    ActionResult<List<CommunityInformation>> list(@RequestBody(required = false) PagingParam<CommunityInfoSearchCondition> pagingParam) {
        if (pagingParam == null ) {
            pagingParam = new PagingParam<>();
        }
        return ActionResult.ok(communityInfoService.list(pagingParam));
    }


    @Override
    protected CrudService<CommunityInformation> getService() {
        return communityInfoService;
    }
}
