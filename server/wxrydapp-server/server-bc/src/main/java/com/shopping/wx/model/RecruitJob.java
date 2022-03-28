package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "recruit_job")
public class RecruitJob {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 招聘人名称
     */
    @Column(name = "recruiter_name")
    private String recruiterName;

    /**
     * 招聘人联系电话
     */
    @Column(name = "recruiter_telephone")
    private String recruiterTelephone;

    /**
     * 招聘岗位名称
     */
    @Column(name = "job_name")
    private String jobName;

    /**
     * 岗位类别名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 招聘岗位介绍和要求
     */
    @Column(name = "job_introduction")
    private String jobIntroduction;

    /**
     * 最小薪资
     */
    @Column(name = "job_salary_min")
    private Integer jobSalaryMin;

    /**
     * 最大薪资
     */
    @Column(name = "job_salary_max")
    private Integer jobSalaryMax;

    /**
     * 工作地址
     */
    @Column(name = "job_address")
    private String jobAddress;

    /**
     * 招聘人数
     */
    @Column(name = "recruiting_number")
    private Integer recruitingNumber;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 浏览量
     */
    @Column(name = "count_view")
    private Integer countView;

    /**
     * 申请次数
     */
    @Column(name = "count_apply")
    private Integer countApply;

    /**
     * 关联 - 招聘者用户id
     */
    @Column(name = "recruiter_openid")
    private String recruiterOpenid;

    /**
     * 关联 - 公司id
     */
    @Column(name = "company_uuid")
    private String companyUuid;

    /**
     * 关联 - 岗位标签表 id
     */
    @Column(name = "category_uuid")
    private String categoryUuid;

    /**
     * 本条状态（0：正常 -1：不显示）
     */
    private Integer status;

    /**
     * 信息录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 获取uuid主键
     *
     * @return id - uuid主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置uuid主键
     *
     * @param id uuid主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取公司名称
     *
     * @return company_name - 公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置公司名称
     *
     * @param companyName 公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 获取招聘人名称
     *
     * @return recruiter_name - 招聘人名称
     */
    public String getRecruiterName() {
        return recruiterName;
    }

    /**
     * 设置招聘人名称
     *
     * @param recruiterName 招聘人名称
     */
    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName == null ? null : recruiterName.trim();
    }

    /**
     * 获取招聘人联系电话
     *
     * @return recruiter_telephone - 招聘人联系电话
     */
    public String getRecruiterTelephone() {
        return recruiterTelephone;
    }

    /**
     * 设置招聘人联系电话
     *
     * @param recruiterTelephone 招聘人联系电话
     */
    public void setRecruiterTelephone(String recruiterTelephone) {
        this.recruiterTelephone = recruiterTelephone == null ? null : recruiterTelephone.trim();
    }

    /**
     * 获取招聘岗位名称
     *
     * @return job_name - 招聘岗位名称
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * 设置招聘岗位名称
     *
     * @param jobName 招聘岗位名称
     */
    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    /**
     * 获取岗位类别名称
     *
     * @return category_name - 岗位类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * 设置岗位类别名称
     *
     * @param categoryName 岗位类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    /**
     * 获取招聘岗位介绍和要求
     *
     * @return job_introduction - 招聘岗位介绍和要求
     */
    public String getJobIntroduction() {
        return jobIntroduction;
    }

    /**
     * 设置招聘岗位介绍和要求
     *
     * @param jobIntroduction 招聘岗位介绍和要求
     */
    public void setJobIntroduction(String jobIntroduction) {
        this.jobIntroduction = jobIntroduction == null ? null : jobIntroduction.trim();
    }

    /**
     * 获取最小薪资
     *
     * @return job_salary_min - 最小薪资
     */
    public Integer getJobSalaryMin() {
        return jobSalaryMin;
    }

    /**
     * 设置最小薪资
     *
     * @param jobSalaryMin 最小薪资
     */
    public void setJobSalaryMin(Integer jobSalaryMin) {
        this.jobSalaryMin = jobSalaryMin;
    }

    /**
     * 获取最大薪资
     *
     * @return job_salary_max - 最大薪资
     */
    public Integer getJobSalaryMax() {
        return jobSalaryMax;
    }

    /**
     * 设置最大薪资
     *
     * @param jobSalaryMax 最大薪资
     */
    public void setJobSalaryMax(Integer jobSalaryMax) {
        this.jobSalaryMax = jobSalaryMax;
    }

    /**
     * 获取工作地址
     *
     * @return job_address - 工作地址
     */
    public String getJobAddress() {
        return jobAddress;
    }

    /**
     * 设置工作地址
     *
     * @param jobAddress 工作地址
     */
    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress == null ? null : jobAddress.trim();
    }

    /**
     * 获取招聘人数
     *
     * @return recruiting_number - 招聘人数
     */
    public Integer getRecruitingNumber() {
        return recruitingNumber;
    }

    /**
     * 设置招聘人数
     *
     * @param recruitingNumber 招聘人数
     */
    public void setRecruitingNumber(Integer recruitingNumber) {
        this.recruitingNumber = recruitingNumber;
    }

    /**
     * 获取优先级
     *
     * @return priority - 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取浏览量
     *
     * @return count_view - 浏览量
     */
    public Integer getCountView() {
        return countView;
    }

    /**
     * 设置浏览量
     *
     * @param countView 浏览量
     */
    public void setCountView(Integer countView) {
        this.countView = countView;
    }

    /**
     * 获取申请次数
     *
     * @return count_apply - 申请次数
     */
    public Integer getCountApply() {
        return countApply;
    }

    /**
     * 设置申请次数
     *
     * @param countApply 申请次数
     */
    public void setCountApply(Integer countApply) {
        this.countApply = countApply;
    }

    /**
     * 获取关联 - 招聘者用户id
     *
     * @return recruiter_openid - 关联 - 招聘者用户id
     */
    public String getRecruiterOpenid() {
        return recruiterOpenid;
    }

    /**
     * 设置关联 - 招聘者用户id
     *
     * @param recruiterOpenid 关联 - 招聘者用户id
     */
    public void setRecruiterOpenid(String recruiterOpenid) {
        this.recruiterOpenid = recruiterOpenid == null ? null : recruiterOpenid.trim();
    }

    /**
     * 获取关联 - 公司id
     *
     * @return company_uuid - 关联 - 公司id
     */
    public String getCompanyUuid() {
        return companyUuid;
    }

    /**
     * 设置关联 - 公司id
     *
     * @param companyUuid 关联 - 公司id
     */
    public void setCompanyUuid(String companyUuid) {
        this.companyUuid = companyUuid == null ? null : companyUuid.trim();
    }

    /**
     * 获取关联 - 岗位标签表 id
     *
     * @return category_uuid - 关联 - 岗位标签表 id
     */
    public String getCategoryUuid() {
        return categoryUuid;
    }

    /**
     * 设置关联 - 岗位标签表 id
     *
     * @param categoryUuid 关联 - 岗位标签表 id
     */
    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid == null ? null : categoryUuid.trim();
    }

    /**
     * 获取本条状态（0：正常 -1：不显示）
     *
     * @return status - 本条状态（0：正常 -1：不显示）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条状态（0：正常 -1：不显示）
     *
     * @param status 本条状态（0：正常 -1：不显示）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取信息录入时间
     *
     * @return create_time - 信息录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置信息录入时间
     *
     * @param createTime 信息录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取扩展字段1
     *
     * @return ext1 - 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 获取扩展字段2
     *
     * @return ext2 - 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    /**
     * 获取扩展字段3
     *
     * @return ext3 - 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}