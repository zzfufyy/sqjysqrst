package com.shopping.wx.service.community_recruitment;

import com.shopping.wx.model.InformationNews;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.infomation_news.InfoNewsSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * @author ljy
 */
public interface InfoNewsService extends CrudService<InformationNews> {

    /**
     * 对新闻列表进行分页查询
     *
     * @param pagingParam 查询条件
     * @return 结果
     */
    List<InformationNews> page(PagingParam<InfoNewsSearchCondition> pagingParam);
}
