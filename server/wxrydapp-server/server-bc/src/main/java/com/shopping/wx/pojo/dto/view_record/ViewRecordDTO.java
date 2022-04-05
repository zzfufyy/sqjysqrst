package com.shopping.wx.pojo.dto.view_record;

import lombok.Data;

/**
 * @ClassName ViewRecordDTO
 * @Description TODO
 * @Author zyw
 * @Date 2022/4/6
 **/
@Data
public class ViewRecordDTO {
    private String id;
    private String candidateOpenid;
    private String companyUuid;
    private String companyName;
    private String portraitPath;
    private String phone;
    private Double distance;
}
