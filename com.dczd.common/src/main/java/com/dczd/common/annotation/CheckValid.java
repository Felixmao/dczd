package com.dczd.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface CheckValid {


    String value() default "";

    /**
     * 0 字符串 1数字 2浮点数
     */
    int type() default 0;

    /**
     * 正则表达式
     *
     * @return
     */
    String regEx() default "";

    /**
     * 最大
     *
     * @return
     */
    long max() default 0L;

    /**
     * 最小
     *
     * @return
     */
    long min() default 0L;

}