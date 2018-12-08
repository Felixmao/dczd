package com.dczd.member.convert;

import com.dczd.member.dto.MemberInfoDto;
import com.dczd.member.pojo.member.MemberPojo;

/**
 * @program: com.dczd.member.convert
 * @description: 会员登录信息转换
 * @author: hou yangkun
 * @create: 2018-11-30
 */
public class MemberInfoConvert {

    /**
     * 会员信息转换Dto
     *
     * @param pojo
     * @return
     */
    public static MemberInfoDto convert(MemberPojo pojo) {
        MemberInfoDto infoDto = new MemberInfoDto();
        infoDto.setToken(pojo.getApp_session());
        infoDto.setMemberInfo(pojo);
        return infoDto;
    }

}
