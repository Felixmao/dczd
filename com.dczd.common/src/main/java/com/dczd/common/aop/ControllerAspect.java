package com.dczd.common.aop;

import com.dczd.common.client.MemberClient;
import com.dczd.common.result.ApiResultData;
import com.dczd.common.result.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @program: com.dczd.common.aop
 * @description: 控制器切面
 * @author: hou yangkun
 * @create: 2018-11-29
 */
@Component
@Aspect
public class ControllerAspect {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Resource
    public MemberClient memberClient;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void controllerPointCut() {
    }

    /**
     * 进行参数校验及Token校验
     */
//    @Before("controllerPointCut()")
//    public void doBefore(JoinPoint joinPoint) {
//
//    }

    @Around("controllerPointCut()")
    public Object doAroundRequestMapping(ProceedingJoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        logger.info("ControllerAspect.doBefore()");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        Object result = new ApiResultData();
//        if (!((CommonResult) result).validateToken(joinPoint, this)) {
//            return result;
//        }
        try {
            result = joinPoint.proceed();
            logger.info(result.toString());
        } catch (Throwable throwable) {
            result = new CommonResult(1, "服务运行异常");
            logger.error("运行异常", throwable);
        }
        return result;
    }

    /**
     * 请求完成之后,打印性能时间
     *
     * @param joinPoint
     */
//    @AfterReturning("controllerPointCut()")
//    public void doAfterReturning(JoinPoint joinPoint) {
//        logger.info("ControllerAspect.doAfterReturning()");
//        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
//    }
}
