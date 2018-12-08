package com.dczd.common.exception;

/**
 * @program: com.dczd.common.exception
 * @description:
 * @author: hou yangkun
 * @create: 2018-12-05
 */
public class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
