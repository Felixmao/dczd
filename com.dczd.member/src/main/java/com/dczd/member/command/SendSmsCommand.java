package com.dczd.member.command;

import com.dczd.common.command.BaseCommand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @program: com.dczd.member.command
 * @description: 发送短信（修改手机号）
 * @author: hou yangkun
 * @create: 2018-11-30
 */
@Setter
@Getter
@ApiModel(description = "发送短信(修改手机号)入参")
public class SendSmsCommand  extends BaseCommand {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @NotNull(message = "手机号不能为空")
    private String telephone;

}
