package sombrero.aop.spring_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Aspect 애노테이션을 붙여서 Aspect 역할을 하는 클래스라는 것을 알려주도록 한다.
 */
@Component
@Aspect
public class PerfAspect {

    @Around("execution(* sombrero.aop.spring_aop.EventService.*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println("# [aop][spring_aop][PerfAspect] time: " + (System.currentTimeMillis() - begin));
        return retVal;
    }

}
