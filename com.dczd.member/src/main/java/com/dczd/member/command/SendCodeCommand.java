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
 * @description: 登录时发送短信
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Setter
@Getter
@ApiModel(description = "登录时发送短信")
public class SendCodeCommand  extends BaseCommand {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为NULL")
    @NotBlank(message = "手机号不能为空")
    @CheckValid(regEx = Constant.TELPHONE_VALIID, value = "手机号格式错误")
    private String telephone;

}
