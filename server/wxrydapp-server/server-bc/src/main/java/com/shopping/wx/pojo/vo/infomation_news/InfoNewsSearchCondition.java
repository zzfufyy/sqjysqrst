package com.shopping.wx.pojo.vo.infomation_news;

import com.shopping.wx.pojo.vo.basic.TimeCondition;
import lombok.Data;

/**
 * @author ljy
 * @date 2022-03-14 16:39
 */
@Data
public class InfoNewsSearchCondition extends TimeCondition{
    /**
     * 文章标题，模糊查询
     */
    String articleTitle;

//    TODO：实现文章类型查询
//    /**
//     * 文章类型
//     */
//    Short articleType;

    /**
     * 社区 id
     */
    String communityId;
}
