package com.dczd.member.controller.api;

import com.dczd.common.result.ApiResultData;
import com.dczd.member.command.SendCodeCommand;
import com.dczd.member.command.SendSmsCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @program: com.dczd.member.controller.api
 * @description: 短信验证码
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Api(description = "会员登录短信发送")
public interface SendApi {

    @ApiOperation(value = "发送短信（修改手机号）")
    @ApiImplicitParam(name = "command", value = "发送短信（修改手机号）", required = true, dataType = "SendSmsCommand")
    public ApiResultData sendSms(SendSmsCommand command);


    @ApiOperation(value = "登录时发送短信")
    @ApiImplicitParam(name = "command", value = "登录时发送短信", required = true, dataType = "SendCodeCommand")
    public ApiResultData sendCode(SendCodeCommand command);


}
