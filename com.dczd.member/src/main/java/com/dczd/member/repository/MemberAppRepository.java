package com.dczd.member.repository;

import com.dczd.member.entity.member.MemberAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @program: com.dczd.member.repository
 * @description: 会员APP
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Repository
public interface MemberAppRepository extends JpaRepository<MemberAppEntity, String>, JpaSpecificationExecutor<MemberAppEntity> {

    public MemberAppEntity findByUserId(Integer userId);

}

