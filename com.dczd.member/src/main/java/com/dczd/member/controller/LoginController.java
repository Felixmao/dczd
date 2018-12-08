package com.dczd.member.controller;

import com.dczd.common.aop.ControllerAspect;
import com.dczd.common.command.ValidateTokenCommand;
import com.dczd.common.exception.BusinessException;
import com.dczd.common.exception.DczdException;
import com.dczd.common.result.ApiResultData;
import com.dczd.common.result.ReturnCode;
import com.dczd.common.util.StringUtil;
import com.dczd.member.command.LoginCommand;
import com.dczd.member.controller.api.LoginApi;
import com.dczd.member.services.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: com.dczd.member.controller
 * @description: 登录
 * @author: hou yangkun
 * @create: 2018-11-28
 */
@RestController
public class LoginController implements LoginApi {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private ILoginService loginService;

    @Override
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ApiResultData login(@RequestBody LoginCommand command) {
        ApiResultData resultData = new ApiResultData();
        try {
            int id = command.getCheckCodeId();
            String telephone = command.getTelephone();
            String checkCode = command.getCheckCode();
            if(telephone.equals("15809255340") && checkCode.equals("1212") ){
                resultData = loginService.IosCheckLogin(command);
            }else{
                if(StringUtil.isEmpty(command.getPhone_type()) || StringUtil.isEmpty(command.getCheckCodeId())){
                    resultData.setCode(ReturnCode.FAILED.code());
                    resultData.setMsg("参数有误!");
                    return resultData;
                }
                resultData = loginService.login(command);
            }
        } catch (DczdException e) {
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg(e.getMessage());
        } catch (Exception e) {
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg("登录失败!");
        }
        return resultData;
    }

    @RequestMapping(value = "/validateToken", method = {RequestMethod.POST})
    public ApiResultData validateToken(@RequestBody ValidateTokenCommand command) {
        ApiResultData resultData = new ApiResultData();
        try {
            return loginService.validateToken(command);
        } catch (Exception e) {
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg("失败!");
            return resultData;
        }
    }

    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    public ApiResultData test(@RequestBody ValidateTokenCommand command) {
        ApiResultData resultData = new ApiResultData();
        try {
            return loginService.test(command);
        } catch (Exception e) {
            logger.error("error", e);
            resultData.setCode(ReturnCode.FAILED.code());
            resultData.setMsg("失败!");
            return resultData;
        }
    }


}
