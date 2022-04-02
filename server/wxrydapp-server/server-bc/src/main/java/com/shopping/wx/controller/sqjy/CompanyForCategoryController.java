package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.constant.AuditConstant;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CompanyForCategory;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.basic.UploadService;
import com.shopping.wx.service.community_recruitment.CompanyForCategoryService;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.service.community_recruitment.UserRecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * @ClassName RecruitCompanyController
 * @Description 公司相关controller
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("/company-for-category")
public class CompanyForCategoryController extends CrudController<CompanyForCategory, String> {

    @Autowired
    private CompanyForCategoryService companyForCategoryService;



    @GetMapping("/info")
    public ActionResult<CompanyForCategory> info(@RequestParam String id) {
        return ActionResult.ok(companyForCategoryService.selectById(id));
    }

    @RequestMapping("/add")
    public ActionResult<String> add(@RequestBody List<CompanyForCategory> categoryList) {

        // 取出岗位类别名字列表
        List<String> categoryNameList = companyForCategoryService.selectCategoryNameListByCompanyUuid(categoryList.get(0).getCompanyUuid());
        // 遍历无重复即插入
        categoryList.forEach(r -> {
            if(!categoryNameList.contains(r.getCategoryName())){
                insert(r);
            }
        });
        return ActionResult.ok();
    }
    @Override
    protected CrudService<CompanyForCategory> getService() {
        return companyForCategoryService;
    }




}
