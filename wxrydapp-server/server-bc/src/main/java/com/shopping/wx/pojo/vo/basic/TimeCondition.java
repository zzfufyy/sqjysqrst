package com.shopping.wx.pojo.vo.basic;

import lombok.*;

import java.util.Date;

/**
 * @author ljy
 * @date 2022-03-16 15:59
 */
@Data
public class TimeCondition {
    /**
     * 开始时间
     */
    Date beginTime;
    /**
     * 结束时间
     */
    Date endTime;
}
