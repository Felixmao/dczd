package com.dczd.member.controller.api;

import com.dczd.common.exception.BusinessException;
import com.dczd.common.result.ApiResultData;
import com.dczd.member.command.LoginCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;

/**
 * @program: com.dczd.member.controller.api
 * @description: 登录对外Swagger接口
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Api(description = "用户登录")
public interface LoginApi {

    @ApiOperation(value = "手机号登录")
    @ApiImplicitParam(name = "command", value = "用户登录信息", required = true, dataType = "LoginCommand")
    public ApiResultData login(LoginCommand command);


}
