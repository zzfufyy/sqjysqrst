package com.shopping.wx.util;

import org.apache.logging.log4j.core.util.UuidUtil;

import java.util.UUID;

/**
 * @author ljy
 * @date 2022-03-14 11:22
 */
public class UUIDUtils {

    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String timeBaseUUID() {
        return UuidUtil.getTimeBasedUuid().toString();
    }
}
