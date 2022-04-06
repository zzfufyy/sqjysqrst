package com.shopping.wx.pojo.dto.recruit_job;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 首页显示的工作信息
 *
 * @author ljy
 * @date 2022-03-25 15:07
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobInfoDTO implements Serializable {

    String jobId;

    String companyId;

    String jobName;

    String companyName;

    String portraitPath;

    Integer jobSalaryMin;

    Integer jobSalaryMax;

    String recruiterOpenid;

    String telephone;

    Double distance;
}
