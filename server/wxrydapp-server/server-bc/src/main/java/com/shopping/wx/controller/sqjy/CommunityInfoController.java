package com.shopping.wx.controller.sqjy;

import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.conf.Page;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.entity.JSON;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.model.District;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.model.UserCandidate;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.CommunityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private DB db;
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

    @RequestMapping("/listbyyx")
    public JSON listbyyx(String name, String keyword, Page page){
        JSON json = new JSON();
        Example example = new Example(District.class);
        Example.Criteria criteria =example.createCriteria();
//        criteria3.andCondition()
//        if(name!=null&&!name.equals("")){
//            System.out.println(name);
//            criteria.andLike("categoryName","%"+name+"%");
////            criteria.andLike("keyword","%"+name+"%");
//        }
//        example.excludeProperties("group by district_name");
        List<District> communityInformationList =db.selectPageByExample(District.class,example,page);


        Example example1 = new Example(CommunityInformation.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("status",0);
        example1.setOrderByClause("priority desc");
        List<CommunityInformation> communityInformationList1 =db.selectPageByExample(CommunityInformation.class,example1,page);
        json.setFlag(true);
        HashMap map = new HashMap();
        map.put("communityInformationList",communityInformationList);
        map.put("communityInformationList1",communityInformationList1);
        json.setObj(map);
        return json;
    }

    @RequestMapping("/listbyyx1")
    public JSON listbyyx1(String name, String keyword, Page page){
        JSON json = new JSON();
        Example example1 = new Example(CommunityInformation.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("status",0);
        criteria1.andEqualTo("ext1",name);
        example1.setOrderByClause("priority desc");
        List<CommunityInformation> communityInformationList1 =db.selectPageByExample(CommunityInformation.class,example1,page);
        json.setFlag(true);
        HashMap map = new HashMap();
        map.put("communityInformationList1",communityInformationList1);
        json.setObj(map);
        return json;
    }


    @RequestMapping("/saveUser")
    public JSON saveUser(String zwid, String areaid,String openid, Page page){
        JSON json = new JSON();
        Example example1 = new Example(UserCandidate.class);
        Example.Criteria criteria1 =example1.createCriteria();
        criteria1.andEqualTo("status",0);
        criteria1.andEqualTo("id",openid);
        List<UserCandidate> UserCandidate =db.selectPageByExample(UserCandidate.class,example1,page);
        UserCandidate.get(0).setExpectCategoryId(zwid);
        UserCandidate.get(0).setExpectCommunityId(areaid);
        db.update(UserCandidate.get(0));
        json.setFlag(true);
        HashMap map = new HashMap();
        json.setObj(map);
        return json;
    }
    @RequestMapping("/getjobandCom")
    public JSON getjobandCom(String zwid, String areaid, Page page){
        JSON json = new JSON();
        String[] zwids = zwid.split(",");
        String[] areaids= areaid.split(",");
        String zwname ="";
        String areaname ="";
        List<JobCategory> joblist = new ArrayList<>();
        List<CommunityInformation> list = new ArrayList<>();
        for (int i = 0; i <zwids.length ; i++) {
            JobCategory jobCategory = db.selectById(zwids[i],JobCategory.class);
            if(i==0){
                zwname+=jobCategory.getCategoryName();
            }else {
                zwname+=","+jobCategory.getCategoryName();
            }
            joblist.add(jobCategory);
        }
        for (int i = 0; i <areaids.length ; i++) {
            CommunityInformation communityInformation = db.selectById(areaids[i],CommunityInformation.class);
            if(i==0){
                areaname+=communityInformation.getCommunityName();
            }else {
                areaname+=","+communityInformation.getCommunityName();
            }
            list.add(communityInformation);
        }
        json.setFlag(true);
        HashMap map = new HashMap();
        map.put("zwname",zwname);
        map.put("areaname",areaname);
        map.put("list",list);
        map.put("joblist",joblist);
        json.setObj(map);
        return json;
    }
    @Override
    protected CrudService<CommunityInformation> getService() {
        return communityInfoService;
    }
}
