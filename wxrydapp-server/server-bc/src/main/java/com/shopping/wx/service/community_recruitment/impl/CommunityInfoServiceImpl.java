package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.CommunityInformation;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.community_info.CommunityInfoSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.CommunityInfoService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 社区信息服务
 *
 * @author ljy
 * @date 2022-03-14 9:13
 */
@Service
public class CommunityInfoServiceImpl extends CrudServiceImpl<CommunityInformation> implements CommunityInfoService {


    @Override
    public List<CommunityInformation> page(PagingParam<CommunityInfoSearchCondition> pagingParam) {
        CommunityInfoSearchCondition condition = pagingParam.getCondition();

        // TODO: 实现社区信息分页
        return pagingByBuilder(pagingParam.getPage(), (builder) -> {
            if (condition != null) {
                builder.where(new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .notEmptyLike(condition.getName(), CommunityInformation::getCommunityName)
                        .getSqls());

                builder.orderByDesc(QueryUtils.getFieldName(CommunityInformation::getCreateTime));
            }else{
                builder.where(new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .getSqls());
                builder.orderByDesc(QueryUtils.getFieldName(CommunityInformation::getCreateTime));
            }
        });
    }

    @Override
    public List<CommunityInformation> list(PagingParam<CommunityInfoSearchCondition> pagingParam) {
        CommunityInfoSearchCondition condition = pagingParam.getCondition();
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<CommunityInformation>()
                        .notDeleted()
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(CommunityInformation::getPriority));
        return selectAllByExample(builder.build());
    }

}
