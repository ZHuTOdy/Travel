package com.travel.utils;

import java.util.UUID;
/**
 * @ClassName UuidUtil
 * @Description TODO 产生UUID随机字符串工具类
 * @Author TOdyZHu
 * @Date 2019-07-29 14:31
 */

public final class UuidUtil {
    private UuidUtil(){}
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-","");
    }
}

