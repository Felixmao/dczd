package com.dczd.common.level;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 会员等级
 * @author: hou yangkun
 * @create: 2018/11/28
 * 1	学士
 * 2	学员
 * 3	VIP学员
 * 4	博士后
 * 5	联合发起人
 * 6	原始发起人
 * 7	专栏会员
 * 8	实战班学员
 * 9	合作商
 * 10	健康使者
 * 11	助教
 * 12	图书众筹人
 */
public enum MemberLevel {

    BACHELOR(1, "学士"),
    STUDENT(2, "学员"),
    VIP_STUDENT(3, "VIP学员"),
    POSTDOCTOR(4, "博士后"),
    COSPONSOR(5, "联合发起人"),
    ORIGINAL_PROMOTER(6, "原始发起人"),
    COLUMN_MEMBERS(7, "专栏会员"),
    ACTUAL_COMBAT_STUDENT(8, "实战班学员"),
    PARTNERS(9, "合作商"),
    HEALTH_ANGEL(10, "健康使者"),
    TEACHING_ASSISTANT(11, "助教"),
    BOOK_CROWDFUNDER(12, "图书众筹人");

    private final int code;

    private String value;

    private static Map<Integer, MemberLevel> codes = new ConcurrentHashMap<Integer, MemberLevel>();

    static {
        for (MemberLevel errorCodeEnum : MemberLevel.values()) {
            codes.put(errorCodeEnum.code(), errorCodeEnum);
        }
    }

    MemberLevel(int code) {
        this.code = code;
    }

    MemberLevel(final int code, final String value) {
        this.code = code;
        this.value = value;
    }

    public int code() {
        return code;
    }

    public String value() {
        return value;
    }

    public static MemberLevel valueOf(int code) {
        if (codes.get(code) != null) {
            return codes.get(code);
        } else {
            return BACHELOR;
        }
    }

}
