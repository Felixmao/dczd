package com.dczd.common.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: com.dczd.common.util
 * @description:
 * @author: hou yangkun
 * @create: 2018-12-07
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface EqualProperty {
    public String fieldName();
}
