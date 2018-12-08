package com.dczd.member.pojo.member;

import javax.persistence.Column;

/**
 * @program: com.dczd.member.pojo.member
 * @description:
 * @author: hou yangkun
 * @create: 2018-12-06
 */
public class MemberAppPojo {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 会员ID
     */
    private Integer userId;

    /**
     * app_session
     */
    private String app_session;

    /**
     * app_openid
     */
    private String app_openid;

    /**
     * APP版本
     */
    private String version;

    /**
     * 手机型号
     */
    private String device;

}
