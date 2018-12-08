package com.dczd.common.exception;

/**
 * @program: com.dczd.common.exception
 * @description:
 * @author: hou yangkun
 * @create: 2018-12-05
 */
public class DczdException  extends BaseException {
    public DczdException(String message) {
        super(message);
    }

    public DczdException(Throwable cause) {
        super(cause);
    }

    public DczdException(String message, Throwable cause) {
        super(message, cause);
    }
}
