package com.dczd.member.controller;

import com.dczd.common.annotation.ParamValid;
import com.dczd.common.result.ApiResultData;
import com.dczd.member.command.SendCodeCommand;
import com.dczd.member.command.SendSmsCommand;
import com.dczd.member.controller.api.SendApi;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: com.dczd.member.controller
 * @description: 短信发送
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@RestController
public class SendController implements SendApi {

    @Override
    @ParamValid
    @RequestMapping(value = "/sendSms", method = {RequestMethod.POST})
    public ApiResultData sendSms(@RequestBody SendSmsCommand command) {

        return null;
    }

    @Override
    @ParamValid
    @RequestMapping(value = "/sendCode", method = {RequestMethod.POST})
    public ApiResultData sendCode(@Validated  @RequestBody SendCodeCommand command) {
        ApiResultData resultData = new ApiResultData();

        return resultData;
    }
}
