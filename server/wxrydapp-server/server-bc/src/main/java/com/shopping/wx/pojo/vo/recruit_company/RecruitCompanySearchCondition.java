package com.shopping.wx.pojo.vo.recruit_company;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName RecruitCompanySearchCondition
 * @Description 公司条件查询类
 * @Author zyw
 * @Date 2022/3/16
 **/
@Data
public class RecruitCompanySearchCondition {
    String id;

    String companyName;

    Integer status;

    Integer flagIdentification;

}
