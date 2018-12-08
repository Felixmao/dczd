package com.dczd.common.result;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @description: API接口返回结果类
 * @author: hou yangkun
 * @create: 2018/11/28
 */
@JsonRootName("result")
public class ApiResultData<T> extends CommonResult {

    private T data;

    public ApiResultData() {
        // 默认成功
        super.setCode(ReturnCode.OK.code());
        super.setMsg(ReturnCode.OK.message());
    }

    public ApiResultData(Integer code, String msg) {
        super(code, msg);
    }

    public ApiResultData(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public ApiResultData setData(T data) {
        this.data = data;
        return this;
    }
}
