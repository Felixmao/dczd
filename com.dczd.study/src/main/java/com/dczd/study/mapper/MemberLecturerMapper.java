package com.dczd.study.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @program: com.dczd.member.mapper
 * @description: 会员讲师
 * @author: hou yangkun
 * @create: 2018-11-30
 */
public interface MemberLecturerMapper {

    public Integer findLecturerIdByUserId(@Param("userId") int userId, @Param("teacId") int teacId, @Param("effective") int effective);
}
