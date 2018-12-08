package com.dczd.member.dto;

import com.dczd.member.pojo.member.MemberPojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: com.dczd.member.dto
 * @description: 用户信息
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Setter
@Getter
@ToString
public class MemberInfoDto {

    private String token;

    private MemberPojo memberInfo;

}
