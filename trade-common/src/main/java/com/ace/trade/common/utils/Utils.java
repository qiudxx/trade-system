package com.ace.trade.common.utils;

import com.ace.trade.common.exception.AceMQException;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * <B>描述: </B>杂七杂八工具类<br/>
 * <B>作者: </B>carl.yu<br/>
 * <B>时间: </B>2017/2/20<br/>
 * <B>版本: </B>v1.0.0 <br/>
 * <B>历史: </B> (版本 作者 时间 注释) <br/>
 */
public class Utils {

    private Utils() {
    }

    public static void checkNotBlank(String target, String msg) throws AceMQException {
        if (StringUtils.isBlank(target)) {
            throw new AceMQException(msg);
        }
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(uuid());
    }


}
