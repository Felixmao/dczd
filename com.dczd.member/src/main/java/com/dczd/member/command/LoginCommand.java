package com.dczd.member.command;

import com.dczd.common.annotation.CheckValid;
import com.dczd.common.command.BaseCommand;
import com.dczd.common.constant.Constant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @program: com.dczd.member.command
 * @description: Login - 手机号登录
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Setter
@Getter
@ApiModel(description = "手机号登录参数")
public class LoginCommand extends BaseCommand {

    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为NULL")
    @NotBlank(message = "手机号不能为空")
    @CheckValid(regEx = Constant.TELPHONE_VALIID, value = "手机号格式错误")
    private String telephone;

    @ApiModelProperty(value = "验证码表的id")
    private Integer checkCodeId;

    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不能为NULL")
    @NotBlank(message = "验证码不能为空")
    private String checkCode;

}
