package com.dczd.common.result;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 通用结果枚举
 * @author: hou yangkun
 * @create: 2018/11/28
 *
 * 0返回正常数据，-1未找到相应用户信息. 1相关错误
 */
public enum ReturnCode {

    OK(0, "OK"),
    FAILED(1, "相关错误"),
    UNKNOWN_ERROR(-1, "未找到相应用户信息");

    private final int code;

    private String message;

    private static Map<Integer, ReturnCode> codes = new ConcurrentHashMap<Integer, ReturnCode>();

    static {
        for (ReturnCode errorCodeEnum : ReturnCode.values()) {
            codes.put(errorCodeEnum.code(), errorCodeEnum);
        }
    }

    ReturnCode(int code) {
        this.code = code;
    }

    ReturnCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static ReturnCode valueOf(int code) {
        if (codes.get(code) != null) {
            return codes.get(code);
        } else {
            return UNKNOWN_ERROR;
        }
    }

}
