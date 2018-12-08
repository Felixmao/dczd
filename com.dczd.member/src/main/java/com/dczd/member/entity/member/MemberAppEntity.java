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

/**
 * @program: com.dczd.member.entity.member
 * @description: 会员APP信息
 * @author: hou yangkun
 * @create: 2018-11-30
 */

@Setter
@Getter
@Entity
@Table(name = "dc_member_app_t")
public class MemberAppEntity implements Serializable {

    private static final long serialVersionUID = -5541430572616061628L;

    /**
     * 自增ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id", columnDefinition = "int")
    private Integer id;

    /**
     * 会员ID
     */
    @Column(nullable = false, name = "user_id", columnDefinition = "int")
    private Integer user_Id;

    /**
     * app_session
     */
    @Column(nullable = true, name = "app_session")
    private String app_session;

    /**
     * app_openid
     */
    @Column(nullable = true, name = "app_openid")
    private String app_openid;

    /**
     * APP版本
     */
    @Column(nullable = true, name = "version")
    private String version;

    /**
     * 手机型号
     */
    @Column(nullable = true, name = "device")
    private String device;

}
