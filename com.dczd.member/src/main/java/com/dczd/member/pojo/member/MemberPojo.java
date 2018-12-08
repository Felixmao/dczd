package com.dczd.member.pojo.member;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: com.dczd.member.pojo.login
 * @description: 用户表
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Setter
@Getter
public class MemberPojo implements Serializable {

    private static final long serialVersionUID = -2574347359608034404L;

    /**
     * 会员等级
     */
    private Integer level_id;

    /**
     * 手机类型
     */
    private String phone_type;

    /**
     * APP SESSION
     */
    private String app_session;

    /**
     * 版本
     */
    private String version;

    /**
     * 头像
     */
    private String headimgurl;

    /**
     * 编号
     */
    private String unionid;

    /**
     * 是否购买
     */
    private Boolean isBuy = false;

    /**
     * 是否绑定微信
     */
    private Boolean binding = false;

    /**
     * 手机
     */
    private String telephone;

    /**
     * 口令
     */
    private Integer attention;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 设备
     */
    private String device;

}
