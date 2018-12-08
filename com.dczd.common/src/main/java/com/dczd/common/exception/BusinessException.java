package com.dczd.common.exception;

/**
 * @program: com.dczd.common.exception
 * @description:
 * @author: hou yangkun
 * @create: 2018-12-06
 */
public class BusinessException  extends BaseException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
