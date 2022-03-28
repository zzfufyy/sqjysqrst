package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_community")
public class UserCommunity {
    /**
     * 自增主键
     */
    @Id
    private String id;

    /**
     * 登录用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * 管理人员姓名
     */
    @Column(name = "staff_name")
    private String staffName;

    /**
     * 管理人员编号
     */
    @Column(name = "staff_id")
    private String staffId;

    /**
     * 管理人员手机号
     */
    @Column(name = "staff_telephone")
    private String staffTelephone;

    /**
     * 管理人员邮箱
     */
    @Column(name = "staff_email")
    private String staffEmail;

    /**
     * 管理人员职位/身份
     */
    @Column(name = "staff_position")
    private String staffPosition;

    /**
     * 用户类型（1：超级管理员，2：普通账号）
     */
    @Column(name = "user_type")
    private Integer userType;

    /**
     * 本条状态（0：正常 -1：失效）
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
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取登录用户名
     *
     * @return user_name - 登录用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置登录用户名
     *
     * @param userName 登录用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取登录密码
     *
     * @return user_password - 登录密码
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * 设置登录密码
     *
     * @param userPassword 登录密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    /**
     * 获取管理人员姓名
     *
     * @return staff_name - 管理人员姓名
     */
    public String getStaffName() {
        return staffName;
    }

    /**
     * 设置管理人员姓名
     *
     * @param staffName 管理人员姓名
     */
    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    /**
     * 获取管理人员编号
     *
     * @return staff_id - 管理人员编号
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * 设置管理人员编号
     *
     * @param staffId 管理人员编号
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    /**
     * 获取管理人员手机号
     *
     * @return staff_telephone - 管理人员手机号
     */
    public String getStaffTelephone() {
        return staffTelephone;
    }

    /**
     * 设置管理人员手机号
     *
     * @param staffTelephone 管理人员手机号
     */
    public void setStaffTelephone(String staffTelephone) {
        this.staffTelephone = staffTelephone == null ? null : staffTelephone.trim();
    }

    /**
     * 获取管理人员邮箱
     *
     * @return staff_email - 管理人员邮箱
     */
    public String getStaffEmail() {
        return staffEmail;
    }

    /**
     * 设置管理人员邮箱
     *
     * @param staffEmail 管理人员邮箱
     */
    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail == null ? null : staffEmail.trim();
    }

    /**
     * 获取管理人员职位/身份
     *
     * @return staff_position - 管理人员职位/身份
     */
    public String getStaffPosition() {
        return staffPosition;
    }

    /**
     * 设置管理人员职位/身份
     *
     * @param staffPosition 管理人员职位/身份
     */
    public void setStaffPosition(String staffPosition) {
        this.staffPosition = staffPosition == null ? null : staffPosition.trim();
    }

    /**
     * 获取用户类型（1：超级管理员，2：普通账号）
     *
     * @return user_type - 用户类型（1：超级管理员，2：普通账号）
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型（1：超级管理员，2：普通账号）
     *
     * @param userType 用户类型（1：超级管理员，2：普通账号）
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取本条状态（0：正常 -1：失效）
     *
     * @return status - 本条状态（0：正常 -1：失效）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条状态（0：正常 -1：失效）
     *
     * @param status 本条状态（0：正常 -1：失效）
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