package com.dczd.member.entity.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: com.dczd.member.entity.member
 * @description: 手机APP登录记录表
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Entity
@Table(name = "dc_member_app_login_log_t")
@Setter
@Getter
public class MemberAppLoginLogEntity implements Serializable {

    private static final long serialVersionUID = -1070989137847608302L;

    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id", columnDefinition = "int")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(nullable = false, name = "user_id", columnDefinition = "int")
    private Integer user_id;

    /**
     * 登录时间
     */
    @Column(nullable = true, name = "login_time", columnDefinition = "datetime")
    private Date login_time;

    /**
     * 手机类型
     */
    @Column(nullable = true, name = "phone_type", columnDefinition = "char")
    private String phone_type;

}
