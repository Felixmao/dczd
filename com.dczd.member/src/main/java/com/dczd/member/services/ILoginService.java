package com.dczd.member.services;

import com.dczd.common.command.ValidateTokenCommand;
import com.dczd.common.exception.BusinessException;
import com.dczd.common.exception.DczdException;
import com.dczd.common.result.ApiResultData;
import com.dczd.member.command.LoginCommand;
import org.springframework.transaction.annotation.Transactional;
import com.dczd.common.exception.DczdException;

/**
 * @program: com.dczd.member.services.impl
 * @description: 用户登录接口
 * @author: hou yangkun
 * @create: 2018-11-29
 */
public interface ILoginService {

    /**
     * ios审核手机号登录
     * @param command
     * @return
     * @throws BusinessException
     */
    public ApiResultData iosCheckLogin(LoginCommand command) throws DczdException;

    /**
     * 手机号登录
     * @param command
     * @return
     * @throws BusinessException
     */
    public ApiResultData login(LoginCommand command) throws DczdException;


    /**
     * 会员token有效性验证
     *
     * @param command
     * @return
     * @throws Exception
     */
    public ApiResultData validateToken(ValidateTokenCommand command) throws Exception;

    /**
     * test
     *
     * @param command
     * @return
     */
    public ApiResultData test(ValidateTokenCommand command);
}
