package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.model.RecruitJob;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.pojo.vo.recruit_job.RecruitJobSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.JobCategoryService;
import com.shopping.wx.service.community_recruitment.RecruitJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 在招职位控制器
 * TODO: 建立 VO 模型
 *
 * @author ljy
 * @date 2022-03-11 11:06
 */
@RestController()
@RequestMapping("/recruit-job")
public class RecruitJobController extends CrudController<RecruitJob, String> {

    final
    RecruitJobService recruitJobService;
    @Autowired
    private JobCategoryService jobCategoryService;

    public RecruitJobController(RecruitJobService recruitJobService) {
        this.recruitJobService = recruitJobService;
    }

    @GetMapping("/page")
    ActionResult<PageInfo<RecruitJob>> pageListRecruitJob(
            @RequestBody PagingParam<RecruitJobSearchCondition> pagingParam) {

        return ActionResult.ok(
                PageInfo.of(recruitJobService.page(pagingParam))
        );
    }

    @RequestMapping("/listByCompanyUuid")
    ActionResult<List<RecruitJob>> listByCompanyUuid(@RequestParam String companyUuid, @RequestBody(required = false) PagingParam<RecruitJobSearchCondition> pagingParam) {
        if (pagingParam == null ) {
            pagingParam = new PagingParam<>();
        }
        return ActionResult.ok(recruitJobService.listByCompanyUuid(companyUuid, pagingParam));
    }

    @PostMapping("/add")
    ActionResult<?> add(@RequestBody RecruitJob recruitJob) {
        insert(recruitJob);
        // 如果在 job_category表中 没有 则添加记录
        if(jobCategoryService.selectCountByCategoryName(recruitJob.getJobName())==0){
            JobCategory jobCategory = new JobCategory();
            jobCategory.setCategoryName(recruitJob.getJobName());
            jobCategoryService.insert(jobCategory);
        }
        return ActionResult.ok();
    }


    @GetMapping("/info")
    ActionResult<RecruitJob> info(@RequestParam("id") String recruitRecordId) {
        RecruitJob recruitJob = query(recruitRecordId);
        return ActionResult.ok(recruitJob);
    }

    @PostMapping("/modify")
    ActionResult<?> modify(@RequestBody RecruitJob recruitJob) {
        update(recruitJob);
        return ActionResult.ok();
    }

    @Override
    protected CrudService<RecruitJob> getService() {
        return recruitJobService;
    }
}
