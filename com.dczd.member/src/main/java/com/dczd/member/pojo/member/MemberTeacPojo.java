package com.dczd.member.pojo.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: com.dczd.member.pojo.member
 * @description: 学员
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Getter
@Setter
@ToString
public class MemberTeacPojo implements Serializable {

    private static final long serialVersionUID = 2700499747141827541L;

    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer user_id;

    /**
     * 老师ID
     */
    private Integer teac_id;

    /**
     * 服务购买时间
     */
    private Date service_begin_time;

    /**
     * 服务结束时间
     */
    private Date service_end_time;

    /**
     * 是否有效
     */
    private Integer effective;

    /**
     * 购买年数
     */
    private Integer years;

    /**
     * 订单号
     */
    private String order_number;

    /**
     * 总价
     */
    private Float total_price;

    /**
     * 是否为赠送课程
     */
    private Integer be_giving;
}
