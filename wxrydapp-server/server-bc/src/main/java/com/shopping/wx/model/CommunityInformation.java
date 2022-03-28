package com.shopping.wx.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "community_information")
public class CommunityInformation {
    /**
     * uuid主键
     */
    @Id
    private String id;

    /**
     * 社区名称
     */
    @Column(name = "community_name")
    private String communityName;

    /**
     * 省份名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 市区名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 区名称
     */
    @Column(name = "district_name")
    private String districtName;

    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 社区地址
     */
    private String address;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 本条信息状态（0：正常 -1：失效）
     */
    private Integer status;

    /**
     * 信息录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 扩展字段3
     */
    private String ext3;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

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
     * 获取社区名称
     *
     * @return community_name - 社区名称
     */
    public String getCommunityName() {
        return communityName;
    }

    /**
     * 设置社区名称
     *
     * @param communityName 社区名称
     */
    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    /**
     * 获取省份名称
     *
     * @return province_name - 省份名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省份名称
     *
     * @param provinceName 省份名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取市区名称
     *
     * @return city_name - 市区名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置市区名称
     *
     * @param cityName 市区名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 获取区名称
     *
     * @return district_name - 区名称
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * 设置区名称
     *
     * @param districtName 区名称
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName == null ? null : districtName.trim();
    }

    /**
     * 获取经度
     *
     * @return lon - 经度
     */
    public Double getLon() {
        return lon;
    }

    /**
     * 设置经度
     *
     * @param lon 经度
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     * 获取纬度
     *
     * @return lat - 纬度
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 设置纬度
     *
     * @param lat 纬度
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 获取社区地址
     *
     * @return address - 社区地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置社区地址
     *
     * @param address 社区地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 获取本条信息状态（0：正常 -1：失效）
     *
     * @return status - 本条信息状态（0：正常 -1：失效）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置本条信息状态（0：正常 -1：失效）
     *
     * @param status 本条信息状态（0：正常 -1：失效）
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
}