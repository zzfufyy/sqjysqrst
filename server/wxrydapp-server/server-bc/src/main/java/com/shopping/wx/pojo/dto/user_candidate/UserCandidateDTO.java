package com.shopping.wx.pojo.dto.user_candidate;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserCandidateDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/3/30
 **/
@Data
public class UserCandidateDTO {
    String candidateOpenid;
    String realName;
    String candidatePortraitPath;
    Integer gender;
    Date birthday;
    Integer expectSalaryMax;
    Integer expectSalaryMin;
    String expectCategoryId;
    String categoryName;
    String expectCommunityId;
    String communityName;
    Double distance;
}
