package com.yed.glhf.common.aspect;

import com.yed.glhf.common.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.util.Arrays;


@Aspect
@Component
public class UnifiedApiLogAspect {


    private static final Integer WARN_TIME = 1000;
    private static final Integer ERROR_TIME = 3000;

    private static final Logger logger = LoggerFactory.getLogger(UnifiedApiLogAspect.class);

    @Pointcut("execution(public * com.yed..*.*(..))")
    public void inSummerPublicMethod() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void inRestController() {
    }

    @Around("inSummerPublicMethod() && inRestController()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // 接收到请求，记录请求内容

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (attributes == null ? null : attributes.getRequest());

        logger.info("UNIFIED_API_LOG_START: request url is {}, http method is {}, class method is {}.{}, args are {} ", (request == null ? "" : request.getRequestURL().toString()), (request == null ? "" : request.getMethod()), proceedingJoinPoint.getSignature().getDeclaringTypeName(), proceedingJoinPoint.getSignature().getName(), Arrays.toString(proceedingJoinPoint.getArgs()));

        // 记录下请求内容
        long startTime = System.currentTimeMillis();
        try {
            Object object = proceedingJoinPoint.proceed();
            logger.info("UNIFIED_API_LOG_PROCEED_SUCCESSFULLY, response is:{}", object);
            return object;
        } catch (ValidationException e) {
            logger.error("UNIFIED_API_LOG_THROW_VALIDATOR_SERVICE_EXCEPTION", e);
            throw e;
        } catch (ServiceException e) {
            logger.error("UNIFIED_API_LOG_THROW_SERVICE_EXCEPTION", e);
            throw e;
        } catch (Throwable t) {
            logger.error("UNIFIED_API_LOG_THROW_THROWABLE", t);
            throw t;
        } finally {
            Long time = System.currentTimeMillis() - startTime;
            if (time > ERROR_TIME) {
                logger.warn("UNIFIED_API_LOG_END_TOO_LONG: used time is {}ms ", time);
            } else if (time > WARN_TIME) {
                logger.warn("UNIFIED_API_LOG_END_LONG: used time is {}ms ", time);
            } else {
                logger.info("UNIFIED_API_LOG_END: used time is {}ms ", time);
            }
        }

    }
}
