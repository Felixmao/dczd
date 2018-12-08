package com.dczd.common.constant;

/**
 * @program: com.dczd.common.constant
 * @description: 常量
 * @author: hou yangkun
 * @create: 2018-11-29
 */
public class Constant {
    /**
     * 所有redis缓存key前缀
     */
    public static final String REDIS_PREFIX = "dc_";

    /**
     * 会员信息缓存前缀 + USERID
     */
    public static final String REDIS_MEMBER_PREFIX = REDIS_PREFIX + "app_member_info_";


    /**
     * 会员登录信息缓存前缀 + USERID
     */
    public static final String REDIS_MEMBER_INFO_PREFIX = REDIS_PREFIX + "member_info_id_";

    /**
     * 手机是否有效
     */
    public static final String TELPHONE_VALIID = "^[1][0-9]{10}$";


}
