package com.shopping.wx.service.community_recruitment.impl;

import com.shopping.wx.managed_mapper.community_recruitment.IRecruitCompanyMapper;
import com.shopping.wx.model.InformationNews;
import com.shopping.wx.model.RecruitCompany;
import com.shopping.wx.pojo.vo.basic.PagingParam;
import com.shopping.wx.pojo.vo.recruit_company.RecruitCompanySearchCondition;
import com.shopping.wx.service.basic.impl.CrudServiceImpl;
import com.shopping.wx.service.community_recruitment.RecruitCompanyService;
import com.shopping.wx.util.query_utils.QueryUtils;
import com.shopping.wx.util.query_utils.WhereClauseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RecruitCompanyServiceImpl
 * @Description 公司相关接口实现
 * @Author zyw
 * @Date 2022/3/11
 **/
@Service
public class RecruitCompanyServiceImpl extends CrudServiceImpl<RecruitCompany> implements RecruitCompanyService {
    @Autowired
    IRecruitCompanyMapper iRecruitCompanyMapper;

    @Override
    public List<RecruitCompany> page(PagingParam<RecruitCompanySearchCondition> pagingParam) {

        RecruitCompanySearchCondition condition = pagingParam.getCondition();

        return pagingByBuilder(pagingParam.getPage(),
                builder -> {
                    if (condition == null) {
                        return;
                    }
                    builder.andWhere(

                            new WhereClauseBuilder<RecruitCompany>()
                                    // 未删除
                                    .notDeleted()
                                    //  姓名 模糊
                                    .notEmptyLike(condition.getCompanyName(), RecruitCompany::getCompanyName)
                                    //  是否认证
                                    .notNullEq(condition.getFlagIdentification(), RecruitCompany::getFlagIdentification)

                                    .getSqls()
                    );

                    // 默认时间降序
                    builder.orderByDesc(QueryUtils.getFieldName(RecruitCompany::getCreateTime));


                }
        );
    }

    @Override
    public Boolean incrCountView(String id) {
        return iRecruitCompanyMapper.incrCountView(id) == 1 ? true : false;
    }

}
