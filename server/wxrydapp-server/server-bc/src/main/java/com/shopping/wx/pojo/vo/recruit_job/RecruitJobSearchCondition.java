package com.shopping.wx.pojo.vo.recruit_job;

import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-15 13:53
 */

@Data
public class RecruitJobSearchCondition {
    // 发布者 id
    String recruiterOpenid;
    // 公司 uuid
    String companyUuid;
    // 岗位 uuid
    String categoryUuid;
    // TODO:  实现上架和下架状态
    // bool isActive
}
