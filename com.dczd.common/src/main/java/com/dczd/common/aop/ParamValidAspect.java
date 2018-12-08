package com.dczd.common.aop;

import com.dczd.common.annotation.CheckValid;
import com.dczd.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @program: com.dczd.common.aop
 * @description: 参数类校验
 * @author: hou yangkun
 * @create: 2018-11-30
 * <p>
 * CheckValid
 */
//@Component
//@Aspect
public class ParamValidAspect {

    private static final Logger logger = LoggerFactory.getLogger(ParamValidAspect.class);

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.dczd.common.annotation.ParamValid)")
    private void paramPointCut() {
    }

    /**
     * 进行参数校验及Token校验
     */
    @Before("paramPointCut()")
    public void doBefore(JoinPoint point) throws IllegalAccessException {
        Object[] paramObj = point.getArgs();
        for (int i = 0; i < paramObj.length; i++) {
            Object obj = paramObj[i];
            Field[] fields = obj.getClass().getDeclaredFields();

            for (int k = 0; k < fields.length; k++) {
                Object objValue = fields[k].get(new Object());
                Annotation[] annotations = fields[k].getAnnotations();
                for (int j = 0; j < annotations.length; j++) {
                    Annotation annotations1 = annotations[i];

                    String str = objValue.toString();
                    if (annotations1 instanceof NotBlank || annotations1 instanceof NotNull) {
                        if (StringUtils.isEmpty(str)) {
                            logger.error(((NotBlank) annotations1).message());
                        }
                    }

                    if (annotations1 instanceof CheckValid) {
                        if (StringUtils.isNotEmpty(str)) {
                            if (StringUtil.matcherStr(str, ((CheckValid) annotations1).regEx())) {
                                logger.error(((CheckValid) annotations1).value());
                            }
                        }

                    }
                }
            }
        }
    }


}
