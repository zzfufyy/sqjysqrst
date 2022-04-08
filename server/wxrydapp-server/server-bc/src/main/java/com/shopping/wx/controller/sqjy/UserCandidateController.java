package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.config.StorageConfig;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.dto.recruit_job.JobInfoDTO;
import com.shopping.wx.pojo.dto.user_candidate.UserCandidateDTO;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.common.Location;
import com.shopping.wx.pojo.vo.user_candidate.UserCandidateSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.UserCandidateService;
import com.vdurmont.emoji.EmojiParser;
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
        // 找不到再插入
        if (userCandidateService.selectById(userCandidate.getId()) == null) {
            String nickName = EmojiParser.parseToHtmlHexadecimal(userCandidate.getRealName());

            userCandidate.setRealName(nickName);
            insert(userCandidate);
        }
        return ActionResult.ok();
    }

    @RequestMapping("/info")
    public ActionResult<UserCandidate> info(String openid) {
        return ActionResult.ok(query(openid));
    }

    @RequestMapping("/modify")
    ActionResult<?> modify(@RequestBody UserCandidate userCandidate) {
        update(userCandidate);
        return ActionResult.ok();
    }

    @PostMapping("/paged-by-distance")
    ActionResult<PageInfo<UserCandidateDTO>> pagedByDistance(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Integer jobSalaryMin,
            @RequestParam(required = false) Integer jobSalaryMax,
            @RequestBody PagingParam<Location> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(userCandidateService.pagedByDistance(categoryName,jobSalaryMin,jobSalaryMax,pagingParam))
        );
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

    @RequestMapping("/uploadPortrait")
    public ActionResult<?> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            UserCandidate userCandidate = getEntityWithId(id);
            userCandidate.setPortraitPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(userCandidate) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    @RequestMapping("/increaseCountView")
    public ActionResult<?> increaseViewCount(@RequestParam String id) {
        userCandidateService.increaseViewCount(id);
        return  ActionResult.ok();
    }


    @Override
    protected CrudService<UserCandidate> getService() {
        return userCandidateService;
    }

    private UserCandidate getEntityWithId(String openid) {
        UserCandidate userCandidate = new UserCandidate();
        userCandidate.setId(openid);
        return userCandidate;
    }
}
