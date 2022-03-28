package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName RecruitCompanyController
 * @Description 公司相关controller
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("/recruit-company")
public class RecruitCompanyController extends CrudController<RecruitCompany, String> {

    @Autowired
    UploadService uploadService;
    @Autowired
    private RecruitCompanyService recruitCompanyService;
    @Autowired
    private UserRecruiterService userRecruiterService;

    @RequestMapping("/page")
    public ActionResult<PageInfo<RecruitCompany>> page(@RequestBody PagingParam<RecruitCompanySearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitCompanyService.page(pagingParam))
        );
    }

    @GetMapping("/info")
    public ActionResult<RecruitCompany> info(@RequestParam String id) {
        return ActionResult.ok(recruitCompanyService.selectById(id));
    }

    /**
     * 添加
     *
     * @param openid
     * @param recruitCompany
     * @return 返回公司id
     */
    @RequestMapping("/add")
    public ActionResult<String> add(@RequestParam String openid, @RequestBody RecruitCompany recruitCompany) {
        insert(recruitCompany);
        userRecruiterService.updateCompanyUuid(openid, recruitCompany.getId());
        return ActionResult.ok(recruitCompany.getId());
    }

    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody RecruitCompany recruitCompany) {
        return ActionResult.ok(update(recruitCompany) == 1);
    }

    @GetMapping("/identifySuccess")
    public ActionResult<Boolean> identifySuccess(@RequestParam String id) {
        RecruitCompany recruitCompany = getEntityWithId(id);
        recruitCompany.setFlagIdentification(AuditConstant.IdentifyStatus.SUCCESS.getValue());
        return updateVO(recruitCompany) ? ActionResult.ok() : ActionResult.error();

    }

    @RequestMapping("/identifyFail")
    public ActionResult<Boolean> identifyFail(@RequestParam String id) {
        RecruitCompany recruitCompany = getEntityWithId(id);
        recruitCompany.setFlagIdentification(AuditConstant.IdentifyStatus.FAIL.getValue());
        return updateVO(recruitCompany) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/incrCountView")
    public ActionResult<Boolean> incrCountView(@RequestParam String id) {
        return recruitCompanyService.incrCountView(id) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/uploadPortrait")
    public ActionResult<Boolean> uploadPortrait(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            RecruitCompany recruitCompany = getEntityWithId(id);
            recruitCompany.setPortraitPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(recruitCompany) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }

    /**
     * 公司相册上传
     *
     * @param id
     * @param file
     * @return com.shopping.base.foundation.result.ActionResult<java.lang.Boolean>
     */
    @RequestMapping("/uploadPhoto")
    public ActionResult<Boolean> uploadPhoto(@RequestParam String id, MultipartFile file) {
        // 取出原有路径
        String photoPath = recruitCompanyService.selectById(id).getPhotoPath();
        // 遍历files 循环上传
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if(uploadResult.getSuccess()){
            photoPath = photoPath + uploadResult.getUploadUriPath() + ";";
        }
        RecruitCompany recruitCompany = getEntityWithId(id);
        recruitCompany.setPhotoPath(photoPath);
        int temp = update(recruitCompany);
        System.out.println(temp);
        return ActionResult.ok(temp == 1);
    }

    @RequestMapping("/uploadLicensePhoto")
    public ActionResult<Boolean> uploadLicensePhoto(@RequestParam String id, MultipartFile file) {
        // 可传 自定义文件夹名
        UploadService.UploadResult uploadResult = uploadService.uploadFile(file, "");
        if (uploadResult.getSuccess()) {
            RecruitCompany recruitCompany = getEntityWithId(id);
            recruitCompany.setLicensePhotoPath(uploadResult.getUploadUriPath());
            return ActionResult.ok(update(recruitCompany) == 1);
        } else {
            return ActionResult.error(uploadResult.getMsg());
        }
    }


    @Override
    protected CrudService<RecruitCompany> getService() {
        return recruitCompanyService;
    }


    private RecruitCompany getEntityWithId(String uuid) {
        RecruitCompany recruitCompany = new RecruitCompany();
        recruitCompany.setId(uuid);
        return recruitCompany;
    }


}
