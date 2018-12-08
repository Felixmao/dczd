package com.dczd.common.client;

import com.dczd.common.command.ValidateTokenCommand;
import com.dczd.common.result.ApiResultData;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 验证用户token有效性
 * @author: hou yangkun
 * @create: 2018/11/30
 */
@FeignClient("member")
public interface MemberClient {
    /**
     * token校验
     */
    @RequestMapping(method = RequestMethod.POST, value = "/validateToken", consumes = "application/json")
    ApiResultData validateToken(@RequestBody ValidateTokenCommand cmd);

}
