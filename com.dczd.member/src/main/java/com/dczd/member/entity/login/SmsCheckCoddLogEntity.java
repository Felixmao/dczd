package com.dczd.member.entity.login;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: com.dczd.member.entity.login
 * @description: 验证码信息
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Setter
@Getter
@Entity
@Table(name = "dc_sms_checkcode_log_t")
public class SmsCheckCoddLogEntity implements Serializable {

    private static final long serialVersionUID = -2719703692218937723L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id", columnDefinition = "int")
    private Integer id;

    /**
     * 手机号
     */
    @Column(nullable = true, name = "tel", columnDefinition = "char")
    private String tel;

    /**
     * 用户ID
     */
    @Column(nullable = true, name = "user_id", columnDefinition = "int")
    private Integer user_id;

    /**
     * 短信验证码
     */
    @Column(nullable = true, name = "code", columnDefinition = "char")
    private String code;

    /**
     * 发送验证码时间
     */
    @Column(nullable = true, name = "createon", columnDefinition = "int")
    private Integer createon;

}
