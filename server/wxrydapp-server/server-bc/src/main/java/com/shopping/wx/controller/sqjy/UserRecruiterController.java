package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_recruiter.UserRecruiterSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserRecruiterController
 * @Description 招聘人相关接口
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("user-recruiter")
public class UserRecruiterController extends CrudController<UserRecruiter, String> {
    @Autowired
    private UserRecruiterService userRecruiterService;

    @RequestMapping("/page")
    public ActionResult<PageInfo<UserRecruiter>> page(@RequestBody PagingParam<UserRecruiterSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userRecruiterService.page(pagingParam))
        );
    }

    @GetMapping("/info")
    public ActionResult<UserRecruiter> info(@RequestParam String id){
        return ActionResult.ok(userRecruiterService.selectById(id));
    }

    @RequestMapping("/add")
    public ActionResult<?> add(@RequestBody UserRecruiter userRecruiter) {
        // 找不到再插入
        if (userRecruiterService.selectById(userRecruiter.getId()) == null) {
            String nickName = EmojiParser.parseToHtmlDecimal(userRecruiter.getRealName());
            userRecruiter.setRealName(nickName);
            insert(userRecruiter);
        }
        return ActionResult.ok();
    }

    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody UserRecruiter userRecruiter) {
        return updateVO(userRecruiter) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/uploadPortrait")
    public ActionResult<Boolean> uploadPortrait() {
        // TODO
        return null;
    }

    private UserRecruiter getEntityWithId(String openid){
        UserRecruiter userRecruiter =  new UserRecruiter();
        userRecruiter.setId(openid);
        return userRecruiter;
    }

    @Override
    protected CrudService<UserRecruiter> getService() {
        return userRecruiterService;
    }

}
