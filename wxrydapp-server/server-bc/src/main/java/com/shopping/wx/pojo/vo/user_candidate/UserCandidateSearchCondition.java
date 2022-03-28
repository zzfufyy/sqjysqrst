package com.shopping.wx.pojo.vo.user_candidate;

import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-16 13:58
 * <p>
 * 用户搜索条件
 */
@Data
public class UserCandidateSearchCondition {

    String realName;
    /**
     * 性别： 0 女 1 男
     */
    Integer gender;
    /**
     * 个人状态
     */
    Integer personnelState;
    /**
     * 期望工作类型
     */
    Integer expectCategoryId;
    /**
     * 社区id
     */
    String communityUuid;
}
