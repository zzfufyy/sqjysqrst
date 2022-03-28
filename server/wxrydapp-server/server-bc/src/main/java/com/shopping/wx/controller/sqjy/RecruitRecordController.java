package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.RecruitRecord;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_record.RecruitRecordSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.RecruitRecordService;
import org.springframework.web.bind.annotation.*;

/**
 * 招聘记录控制器
 * TODO: 建立 VO 模型
 *
 * @author ljy
 * @date 2022-03-16 16:44
 */
@RestController
@RequestMapping("/recruit-record")
public class RecruitRecordController extends CrudController<RecruitRecord, String> {

    final
    RecruitRecordService recruitRecordService;

    public RecruitRecordController(RecruitRecordService recruitRecordService) {
        this.recruitRecordService = recruitRecordService;
    }

    @PostMapping("/add")
    ActionResult<?> add(@RequestBody RecruitRecord recruitRecord) {
        insert(recruitRecord);
        return ActionResult.ok();
    }

    @GetMapping("/info")
    ActionResult<RecruitRecord> info(String recordId) {
        return ActionResult.ok(query(recordId));
    }

    @PostMapping("/modify")
    ActionResult<?> modify(@RequestBody RecruitRecord recruitRecord) {
        update(recruitRecord);
        return ActionResult.ok();
    }

    @GetMapping("/page")
    ActionResult<PageInfo<RecruitRecord>> page(PagingParam<RecruitRecordSearchCondition> pagingParam) {
        return ActionResult.ok(
                PageInfo.of(recruitRecordService.page(pagingParam))
        );
    }

    @Override
    protected CrudService<RecruitRecord> getService() {
        return recruitRecordService;
    }
}
