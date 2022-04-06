package com.shopping.wx.pojo.vo.recruit_company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName RecruitCompanySearchCondition
 * @Description 公司条件查询类
 * @Author zyw
 * @Date 2022/3/16
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitCompanySearchCondition implements Serializable {
    String id;

    String companyName;

    Integer status;

    Integer flagIdentification;

}
