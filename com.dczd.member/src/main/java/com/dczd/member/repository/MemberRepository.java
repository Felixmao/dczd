package com.dczd.member.repository;

import com.dczd.member.entity.login.SmsCheckCoddLogEntity;
import com.dczd.member.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: com.dczd.member.repository
 * @description: 用户JPA
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String>, JpaSpecificationExecutor<MemberEntity> {

    public MemberEntity findByUserId(Integer user_id);

    public MemberEntity findByTelephone(String telephone);
}
