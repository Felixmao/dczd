package com.dczd.member.repository;

import com.dczd.member.entity.member.MemberAppLoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: com.dczd.member.repository
 * @description: 会员APP登录日志
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Repository
public interface MemberAppLoginLogRepository extends JpaRepository<MemberAppLoginLogEntity, String>, JpaSpecificationExecutor<MemberAppLoginLogEntity> {


}