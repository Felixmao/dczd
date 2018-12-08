package com.dczd.member.pojo.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: com.dczd.member.mapper
 * @description: 会员讲师
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Setter
@Getter
@ToString
public class MemberLecturerPojo implements Serializable {

    private static final long serialVersionUID = 2930996572260437712L;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 会员ID
     */
    private Integer user_id;

    /**
     * 讲师ID
     */
    private Integer teac_id;

    /**
     * 加入时间
     */
    private Date join_time;

    /**
     * 购买时价格
     */
    private BigDecimal price;

    /**
     * 购买成功后对应的订单编号
     */
    private String order_sn;

    /**
     * 是否有效
     */
    private Integer effective;

    /**
     * 是否赠送
     */
    private Integer be_giving;

}
