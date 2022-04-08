package com.shopping.wx.pojo.dto.candidate_for_category;

import lombok.Data;

/**
 * @ClassName CandidateForCategoryDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/8
 **/
@Data
public class CandidateForCategoryDTO {
    String id;
    String candidateOpenid;
    String categoryUuid;
    String categoryName;

}
