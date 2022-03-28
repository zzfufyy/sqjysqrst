package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.model.UserRecruiter;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.job_category.JobCategorySearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName JobCategoryController
 * @Description 岗位分类接口  --  1级分类
 * @Author zyw
 * @Date 2022/3/14
 **/
@RestController
@RequestMapping("job-category")
public class JobCategoryController extends CrudController<JobCategory, String> {
    @Autowired
    private JobCategoryService jobCategoryService;


    @RequestMapping("/page")
    public ActionResult<PageInfo<JobCategory>> page(@RequestBody PagingParam<JobCategorySearchCondition> pagingParam) {
        return ActionResult.ok(PageInfo.of(jobCategoryService.page(pagingParam)));
    }

    @GetMapping("/info")
    public ActionResult<JobCategory> info(String id) {
        return ActionResult.ok(infoVO(id, JobCategory::new));
    }


    @RequestMapping("/modify")
    public ActionResult<Boolean> modify(@RequestBody JobCategory jobCategory) {
        return ActionResult.ok(updateVO(jobCategory));
    }


    @RequestMapping("/incrCountView")
    public ActionResult<Boolean> incrCountView(@RequestParam String id) {
        return jobCategoryService.incrCountView(id) ? ActionResult.ok() : ActionResult.error();
    }

    @RequestMapping("/list")
    ActionResult<List<JobCategory>> list(@RequestBody(required = false) PagingParam<JobCategorySearchCondition> pagingParam) {
        if (pagingParam == null) {
            pagingParam = new PagingParam<>();
        }
        return ActionResult.ok(jobCategoryService.list(pagingParam));
    }


    @Override
    protected CrudService<JobCategory> getService() {
        return jobCategoryService;
    }


}




