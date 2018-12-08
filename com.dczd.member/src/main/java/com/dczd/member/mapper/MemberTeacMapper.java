package com.dczd.member.mapper;

import com.dczd.member.pojo.member.MemberTeacPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: com.dczd.member.mapper
 * @description: 学员
 * @author: hou yangkun
 * @create: 2018-11-30
 */
public interface MemberTeacMapper {

    public Integer findTeacIdByUserId(@Param("userId") int userId, @Param("effective") int effective);

    public List<MemberTeacPojo> findAll();



}
