package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.config.StorageConfig;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.UserCandidateService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 应聘者用户控制器
 *
 * @author ljy
 * @date 2022-03-11 11:06
 */
@RestController()
@RequestMapping("/user-candidate")
public class UserCandidateController extends CrudController<UserCandidate, String> {

    final
    UserCandidateService userCandidateService;

    final UploadService uploadService;

    final StorageConfig storageConfig;

    public UserCandidateController(UserCandidateService userCandidateService, UploadService uploadService, StorageConfig localStorageConfig, StorageConfig storageConfig) {
        this.userCandidateService = userCandidateService;
        this.uploadService = uploadService;
        this.storageConfig = storageConfig;
    }

    @RequestMapping("/add")
    ActionResult<?> add(@RequestBody UserCandidate userCandidate) {
        insert(userCandidate);
        return ActionResult.ok();
    }

    @RequestMapping("/info")
    public ActionResult<UserCandidate> info(@RequestParam String openid) {
        return ActionResult.ok(query(openid));
    }

    @RequestMapping("/modify")
    ActionResult<?> modify(@RequestBody UserCandidate userCandidate) {
        update(userCandidate);
        return ActionResult.ok();
    }


    /**
     * 查看用户，例如查看简历，需要自增，这里仅做测试用途
     */
    @GetMapping("/check")
    @Deprecated
    public ActionResult<Boolean> check(String openid) {
        userCandidateService.increaseViewCount(openid);
        return ActionResult.ok();
    }

    @RequestMapping("/page")
    public ActionResult<PageInfo<UserCandidate>> page(
            @RequestBody PagingParam<UserCandidateSearchCondition> pagingParam) {

        return ActionResult.ok(
                PageInfo.of(userCandidateService.page(pagingParam))
        );
    }

    @PostMapping("/uploadPortrait")
    public ActionResult<String> uploadPortrait(MultipartFile file) {
        UploadService.UploadResult result = uploadService.uploadFile(file);

        if (result.getSuccess()) {
            return ActionResult.ok(
                    result.getUploadUriPath()
            );
        } else {
            return ActionResult.error(
                    result.getMsg()
            );
        }
    }

    @Override
    protected CrudService<UserCandidate> getService() {
        return userCandidateService;
    }
}
