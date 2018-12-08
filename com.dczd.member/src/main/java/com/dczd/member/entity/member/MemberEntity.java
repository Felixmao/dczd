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
 * @program: com.dczd.member.entity.login
 * @description: 用户信息
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Getter
@Setter
@Entity
@Table(name = "dc_member_t")
public class MemberEntity implements Serializable {

    private static final long serialVersionUID = -4890827100147647280L;

    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "user_id", columnDefinition = "int")
    private Integer user_Id;

    /**
     * 手机
     */
    @Column(nullable = true, name = "telephone")
    private String telephone;

    /**
     * 注册时间
     */
    @Column(nullable = true, name = "register_time")
    private Date registerTime;
}
