package com.shopping.wx.controller.sqjy;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.shopping.base.foundation.result.ActionResult;
import com.shopping.wx.conf.DB;
import com.shopping.wx.controller.basic.CrudController;
import com.shopping.wx.model.InformationNews;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.infomation_news.InfoNewsSearchCondition;
import com.shopping.wx.service.basic.CrudService;
import com.shopping.wx.service.community_recruitment.InfoNewsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/information-news")
public class NewsInformationController extends CrudController<InformationNews, String> {

    @Autowired
    InfoNewsService infoNewsService;

    @Autowired
    private DB db;

    @RequestMapping(value = "/searchNewsList", method = RequestMethod.GET)
    public JSONObject searchNewsList(String effectCommunityId) throws IOException {
        JSONObject json = new JSONObject();
        Example example = new Example(InformationNews.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("status", "0");
        if (effectCommunityId != null) {
            criteria.andEqualTo("effectCommunityId", effectCommunityId);
        }
        List<InformationNews> informationNewsList = db.selectAllByExample(InformationNews.class, example);
        json.put("informationNewsList", informationNewsList);
        return json;
    }


    @RequestMapping("/page")
    public ActionResult<PageInfo<InformationNews>> page(
            @RequestBody PagingParam<InfoNewsSearchCondition> pagingParam) {

        return ActionResult.ok(
                PageInfo.of(infoNewsService.page(pagingParam))
        );
    }

    @GetMapping("/info")
    public ActionResult<InformationNews> info(@RequestParam String id) {
        return ActionResult.ok(infoNewsService.selectById(id));
    }

    @RequestMapping("/add")
    public ActionResult<Boolean> add(@RequestBody InformationNews informationNews) {
        return ActionResult.ok(insert(informationNews) == 1);
    }

    @RequestMapping("/listOrderByReleaseTime")
    ActionResult<List<InformationNews>> listOrderByReleaseTime() {
        return ActionResult.ok(infoNewsService.listOrderByReleaseTime());
    }

    @Override
    protected CrudService<InformationNews> getService() {
        return infoNewsService;
    }
}
