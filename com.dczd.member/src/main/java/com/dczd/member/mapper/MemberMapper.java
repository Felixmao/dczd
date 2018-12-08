package com.dczd.member.mapper;

import com.dczd.member.pojo.member.MemberPojo;
import org.apache.ibatis.annotations.Param;

/**
 * @program: com.dczd.member.mapper
 * @description: 用户查询
 * @author: hou yangkun
 * @create: 2018-11-29
 */
public interface MemberMapper {


    public MemberPojo findMemberByTel(@Param("tel") String tel);

    public MemberPojo findMemberByUserId(@Param("userId") int userId);

    public void updateVersionAndDevice(@Param("version") String version, @Param("device") String device, @Param("userId") int userId);
}
