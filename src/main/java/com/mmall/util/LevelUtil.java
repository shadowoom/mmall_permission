package com.mmall.util;


import org.apache.commons.lang3.StringUtils;

/**
 * permission
 * com.mmall.util
 * Created by Zhang Chen
 * 4/22/2018
 */
public class LevelUtil {

    public final static String SEPARATOR = ".";

    public final static String ROOT = "0";

    public static String calculateLevel(String parentLevel, int parentId) {
        if(StringUtils.isBlank(parentLevel)) {
            return ROOT;
        }
        else {
            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }
    }
}
