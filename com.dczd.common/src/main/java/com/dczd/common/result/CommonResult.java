package com.dczd.common.result;

import com.dczd.common.aop.ControllerAspect;
import com.dczd.common.command.BaseCommand;
import com.dczd.common.command.ValidateTokenCommand;
import lombok.ToString;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @description:
 * @author: hou yangkun
 * @create: 2018/11/28
 */
@ToString
public class CommonResult {

    private int code;

    private String msg;

    public CommonResult() {
        // 默认成功
        this.code = ReturnCode.OK.code();
        this.msg = ReturnCode.OK.message();
    }

    public CommonResult(Integer resultCode, String message) {
        this.code = resultCode.intValue();
        this.msg = message;
    }

    public <T extends CommonResult> T fillResult(ReturnCode result) {
        this.setCode(result.code());
        this.setMsg(result.message());
        return (T) this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 验证令牌
     * @param proceedingJoinPoint
     * @param controllerAspect
     */
    public Boolean validateToken(ProceedingJoinPoint proceedingJoinPoint, ControllerAspect controllerAspect) {
        String token = "";
        BaseCommand command = null;
        boolean validateToken = true;
        Object[] args = proceedingJoinPoint.getArgs();
        for (Object arg : args) {
            if (BaseCommand.class.isAssignableFrom(arg.getClass().getSuperclass())) {
                command = (BaseCommand) arg;
                validateToken = command.validateToken();
                token = command.getToken();
                break;
            }
        }
        if (null == command) {
            return false;
        }

        if (validateToken) {
            ValidateTokenCommand validateTokenCommand = new ValidateTokenCommand();
            validateTokenCommand.setToken(token);
            ApiResultData tokenResult = controllerAspect.memberClient.validateToken(validateTokenCommand);
            if (null == tokenResult.getData()) {
                if (tokenResult.getCode() != 0) {
                    validateTokenCommand.setUser_id(tokenResult.getCode());
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}