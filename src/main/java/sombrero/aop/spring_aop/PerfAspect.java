package sombrero.aop.spring_aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Aspect 애노테이션을 붙여서 Aspect 역할을 하는 클래스라는 것을 알려주도록 한다.
 * 빈으로 등록해야 하니까 (컴포넌트 스캔을 사용한다면) @Component도 추가.
 */
@Component
@Aspect
public class PerfAspect {

    /**
     * 아래에서 인자로 받는 ProceedingJoinPoint 는 => 어드바이스가 적용되는 대상.
     * (이 예제에서는 SimpleEventService2 클래스의 createEvent(), publishEvent() 메서드 자체라고 보면 된다.)
     * 메서드 invocation과 비슷한 개념.
     * 메서드 실행 => pjp.proceed();
     */
    @Around("execution(* sombrero.aop.spring_aop.EventService.*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed();
        System.out.println("# [aop][spring_aop][PerfAspect] time: " + (System.currentTimeMillis() - begin));
        return retVal;
    }

}
