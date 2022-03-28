package com.shopping.wx.pojo.dto.recruit_job;


import lombok.Data;

/**
 * 首页显示的工作信息
 *
 * @author ljy
 * @date 2022-03-25 15:07
 */

@Data
public class JobInfoDTO {

    String jobId;

    String companyId;

    String jobName;

    String companyName;

    String portraitPath;

    Integer jobSalaryMin;

    Integer jobSalaryMax;

    Double distance;
}
