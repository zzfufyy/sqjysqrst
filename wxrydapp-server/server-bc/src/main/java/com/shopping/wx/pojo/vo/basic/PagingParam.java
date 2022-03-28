package com.shopping.wx.pojo.vo.basic;

import com.shopping.wx.conf.Page;
import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-14 15:15
 * <p>
 * 分页参数和包含的搜索条件
 */
@Data
public class PagingParam<T> {
    Page page;
    /**
     * 分页附带的搜索条件，注意需要检查条件是否为 null，
     */
    T condition;
}
