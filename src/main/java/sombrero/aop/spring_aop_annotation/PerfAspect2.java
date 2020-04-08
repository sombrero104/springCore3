package sombrero.aop.spring_aop_annotation;

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
public class PerfAspect2 {

    /**
     * 아래에서 인자로 받는 ProceedingJoinPoint 는 => 어드바이스가 적용되는 대상.
     * (이 예제에서는 SimpleEventService3 클래스의 createEvent(), publishEvent() 메서드 자체라고 보면 된다.)
     * 메서드 invocation과 비슷한 개념.
     * 메서드 실행 => pjp.proceed();
     */
    /**
     * @Around 에는 포인트컷 이름을 주거나 포인트컷을 직접 정의할 수 있다.
     * @Around(...) => logPert() 어드바이스를 어떻게 적용할 것인가.
     * @Around 는 메서드(여기에서는 createEvent(), publishEvent())를 감싼 형태로 적용됨.
     * 때문에 메서드 호출 이전에도 뭔가를 할 수 있고, 이후에도 뭔가를 할 수 있다.
     * 또는 메소드에서 발생한 에러를 잡아서 에러가 발생했을 때 특정한 일을 할수도 있다.
     */
    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed(); // 메서드 호출
        System.out.println("# [aop][spring_aop_annotation][PerfAspect2] time: " + (System.currentTimeMillis() - begin));
        return retVal;
    }

}
