package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.model.InformationNews;
import com.shopping.wx.model.JobCategory;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.infomation_news.InfoNewsSearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.InfoNewsService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author ljy
 */
@Service
public class InfoNewsServiceImpl extends CrudServiceImpl<InformationNews> implements InfoNewsService {


    @Override
    public List<InformationNews> page(PagingParam<InfoNewsSearchCondition> pagingParam) {
        InfoNewsSearchCondition condition = pagingParam.getCondition();

        return pagingByBuilder(pagingParam.getPage(), (builder) -> {
            // Where 子语句
            builder.andWhere(new WhereClauseBuilder<InformationNews>()
                    // 未删除
                    .notDeleted()
                    // 如果标题不为空，模糊查询标题
                    .notEmptyLike(condition.getArticleTitle(), InformationNews::getArticleTitle)
                    // 提供了社区 id，设置相等条件
                    .notEmptyEq(condition.getCommunityId(), InformationNews::getEffectCommunityId)
                    // 范围查询时间
                    .dateRange(condition, InformationNews::getArticleReleaseTime)
                    .getSqls()
            );

            // OrderBy 子句
            // 根据优先级降序排序
            builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getPriority));
        });
    }

    @Override
    public List<InformationNews> listOrderByReleaseTime() {
        Example.Builder builder = new Example.Builder(getEntityClass());
        builder.where(
                new WhereClauseBuilder<InformationNews>()
                        .notDeleted()
                        .getSqls()
        );
        builder.orderByDesc(QueryUtils.getFieldName(InformationNews::getArticleReleaseTime));
        return selectAllByExample(builder.build());
    }
}
