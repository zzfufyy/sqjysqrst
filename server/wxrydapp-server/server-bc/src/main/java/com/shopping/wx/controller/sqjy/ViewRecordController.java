package com.shopping.wx.controller.sqjy;

import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.*;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.ViewRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("view-record")
public class ViewRecordController extends CrudController<ViewRecord, String> {
    @Autowired
    private ViewRecordService viewRecordService;


    @Autowired
    private DB db;

    //查询被浏览量
    @RequestMapping("/list")
    public JSON list(Page page, int pages, String openid) {
        JSON json = new JSON();
        HashMap map = new HashMap();
        Example example = new Example(ViewRecord.class);
        page.setPage(pages);
        page.setRows(10);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", 0);
        criteria.andEqualTo("candidateOpenid", openid);
        example.setOrderByClause("create_time desc");
        List<ViewRecord> viewRecordList = db.selectPageByExample(ViewRecord.class, example, page);
        List<UserRecruiter> userRecruiterArrayList = new ArrayList<>();
        List<RecruitCompany> recruitCompanyArrayList = new ArrayList<>();
        for (int i = 0; i < viewRecordList.size(); i++) {
            UserRecruiter userRecruiter = db.selectById(viewRecordList.get(i).getRecruiterOpenid(), UserRecruiter.class);
            RecruitCompany recruitCompany = db.selectById(viewRecordList.get(i).getCompanyUuid(), RecruitCompany.class);
            userRecruiterArrayList.add(userRecruiter);
            recruitCompanyArrayList.add(recruitCompany);

        }

        Example example1 = new Example(RecruitRecord.class);
        Example.Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("candidateOpenid", openid);
        criteria1.andEqualTo("status", 0);
        criteria1.andNotEqualTo("recruiterOpenid", "");
        List<RecruitRecord> recruitRecords = db.selectAllByExample(RecruitRecord.class, example1);
        UserCandidate userCandidate = db.selectById(openid, UserCandidate.class);
        map.put("userRecruiterArrayList", userRecruiterArrayList);
        map.put("recruitCompanyArrayList", recruitCompanyArrayList);
        map.put("viewRecordList", viewRecordList);
        map.put("userCandidate", userCandidate);
        map.put("recruitRecords", recruitRecords);
        json.setFlag(true);
        json.setObj(map);
        return json;
    }


    @RequestMapping("/add")
    public ActionResult<Boolean> add(@RequestBody ViewRecord viewRecord) {
        List<ViewRecord> listViewRecord = viewRecordService.select(viewRecord);
        ViewRecord currentViewRecord = (listViewRecord.size() == 0)? null: listViewRecord.get(0);
        if (currentViewRecord == null) {
            // 无记录插入
            insert(viewRecord);
        }else{
            // 如果有记录 更新至最新时间
            currentViewRecord.setCreateTime(new Date());
            update(currentViewRecord);
        }
        return ActionResult.ok(true);
    }


    @Override
    protected CrudService<ViewRecord> getService() {
        return viewRecordService;
    }
}
