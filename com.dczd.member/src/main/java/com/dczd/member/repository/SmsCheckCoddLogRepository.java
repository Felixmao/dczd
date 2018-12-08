package com.dczd.member.repository;

import com.dczd.member.entity.login.SmsCheckCoddLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @description: 短信验证码登录
 * @author: hou yangkun
 * @create: 2018/11/28
 */
@Repository
public interface SmsCheckCoddLogRepository extends JpaRepository<SmsCheckCoddLogEntity, String>, JpaSpecificationExecutor<SmsCheckCoddLogEntity> {

    public SmsCheckCoddLogEntity findByIdAndTel(Integer id, String tel);

}
