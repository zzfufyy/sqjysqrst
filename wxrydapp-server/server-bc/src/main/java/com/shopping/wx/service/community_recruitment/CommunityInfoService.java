package com.shopping.wx.service.community_recruitment;

import com.github.pagehelper.page.PageParams;
import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.service.basic.CrudService;

import java.util.List;

/**
 * 社区信息服务
 *
 * @author ljy
 * @date 2022-03-14 9:13
 */
public interface CommunityInfoService extends CrudService<CommunityInformation> {
    /**
     * 根据条件分页查询
     *
     * @param pagingParam 分页条件
     * @return 结果
     */
    List<CommunityInformation> page(PagingParam<CommunityInfoSearchCondition> pagingParam);

    /**
     * 根据条件查询 List
     *
     * @param
     * @return java.util.List<com.shopping.wx.model.CommunityInformation>
     */
    List<CommunityInformation> list(PagingParam<CommunityInfoSearchCondition> pagingParam);


}
