# 스프링 핵심기술 (3편)
<br/>

# 스프링 AOP
Aspect-oriented Programming(AOP)는 OOP를 보완하는 수단으로, <br/>
흩어진 Aspect를 모듈화 할 수 있는 프로그래밍 기법. <br/>
<br/>

![aop](./images/aop.png)<br/>
<br/>

## AOP 주요 개념
#### (1) Aspect(모듈)<br/>
어드바이스와 포인트컷을 합친 개념.<br/>
Aspect = Advice + Pointcut (해야할 일들을 어디에 적용?)<br/>

#### (2) Target(적용 대상 클래스)<br/>
#### (3) Advice(해야할 일들)<br/>
#### (4) Join point(합류점)<br/>
끼어들 수 있는 지점.<br/>
메서드 실행 시점을 흔하게 사용. (메서드 실행할 때 Advice를 끼워넣어라.)<br/>
생성자 호출 직전, 생성자 호출 했을 때, 필드에 접근하기 전, 필드에서 값을 가져갔을 때 등등<br/>
#### (5) Pointcut(어디에 적용)<br/>
Join point의 구체적인 sub set.<br/>
'A라는 클래스의 b라는 메서드를 호출할 때 적용해라.'<br/>
<br/>

## AOP 구현체
- https://en.wikipedia.org/wiki/Aspect-oriented_programming <br/>
- 자바에서는 AspectJ, 스프링 AOP <br/>
<br/><br/>

# AOP 적용 방법
#### (1) 컴파일 타임<br/>
자바 파일을 클래스 파일로 만들 때 바이트코드들을 조작. 조작이 된 바이트코드를 생성해냄.<br/>
별도의 컴파일링이 더 필요.<br/>
바이트코드를 조작하여 클래스로 만들어버리므로, 로드 타임과 런 타임 시 성능에 영향이 없음.<br/>
#### (2) 로드 타임<br/>
A라는 자바 파일이 있을 때, 컴파일은 순수하게 A클래스 그 자체로 컴파일.<br/>
A클래스를 로딩하는 시점에 변경. 클래스 로딩 시점에 약간의 부하 발생. <br/>
로드 타임 위버를 설정해야 함. (로드 타임 자바 에이전트를 설정해줘야 함.)<br/>
AspectJ를 사용할 수 있으므로, 다양한 문법 사용 가능. <br/>
A클래스의 바이트코드 자체는 변경이 없지만, 로딩한 JVM 메모리 상에서는 어드바이스가 같이 있는 상태로 로딩됨.<br/>
> * 로드 타임 위빙: 로딩하는 클래스 정보를 변경하는 방법.

#### (3) 런 타임<br/>
프록시 기반의 AOP. 스프링 AOP가 사용하는 방법. <br/>
A라는 빈에 Aspect가 가진 어떠한 메소드(포인트컷)에 어드바이스를 적용해야 된다는 것을 스프링이 알고 있음. <br/>
이미 클래스는 읽은 후 런타임 과정에서 A라는 타입의 빈을 만들 때, A라는 타입의 프록시 빈을 만듬.(A라는 빈을 감싼 프록시 빈을 만듬.) <br/>
빈을 만들때만 초기 과정에 약간의 성능 부하. <br/>
위에서의 컴파일 타임과 로드 타임과 같이 별도의 컴파일러나 자바 에이전트(로드 타임 위버)를 설정하지 않아도 되고, <br/>
문법이 쉽고 AOP에 대해 많이 공부하지 않아도 된다. <br/>
<br/><br/>

# 프록시 기반 AOP
### 스프링 AOP 특징
- 프록시 기반의 AOP 구현체 <br/>
- 스프링 빈에만 AOP를 적용할 수 있다. <br/>
- 모든 AOP 기능을 제공하는 것이 목적이 아니라, <br/>
스프링 IoC와 연동하여 엔터프라이즈 애플리케이션에서 가장 흔한 문제에 대한 해결책을 제공하는 것이 목적.<br/>

### 프록시 패턴
기존 코드 변경 없이 접근 제어 또는 부가 기능 추가.<br/>

![proxy_pattern](./images/proxy_pattern.png)<br/>

#### ** 프록시 패턴을 구현해보자.
=> sombrero.aop.proxy_pattern 패키지 참조.<br/>
그런데..<br/>
- 매번 프록시 클래스를 작성해야 하는가?<br/>
- 여러 클래스 여러 메소드에 적용하려면?<br/>
- 객체들 관계도 복잡하고..<br/>

### 그래서 등장한 것이 스프링 AOP
스프링 IoC 컨테이너가 제공하는 기반 시설과 Dynamic 프록시를 사용하여(같이 혼합해서) 여러 복잡한 문제 해결.<br/>
#### * 동적 프록시
동적으로 프록시 객체를 생성하는 방법.<br/>
**_동적으로(런타임 시) 어떤 객체의 프록시 객체를 런타임 때 만드는 방법._** <br/>
자바가 제공하는 방법은 인터페이스 기반 프록시 생성 방법.<br/>
CGlib은 클래스 기반 프록시도 지원.<br/>
#### * 스프링 IoC
**_기존 빈을 대체하는 동적 프록시 빈을 만들어 등록 시켜준다._** <br/>
클라이언트 코드 변경 없음.<br/>
<pre>
AbstractAutoProxyCreator implements BeanPostProcessor
</pre>

> BeanPostProcessor: 어떤 빈이 등록되면 그 빈을 가공할 수 있는 라이프사이클 인터페이스.<br/>

> 예를 들어 sombrero.aop.proxy_pattern.SimpleEventService가 빈으로 등록이 되면 <br/>
스프링이 AbstractAutoProxyCreator라는 BeanPostProcessor로 <br/>
SimpleEventService 빈을 감싸는 프록시 빈을 만들어서 그 프록시 빈을 SimpleEventService 빈 대신에 등록해준다.<br/>
<br/>
<br/>
>
# 스프링 AOP (@AOP)
@AOP 의존성 추가. <br/>
<pre>
‹dependency›
    ‹groupId›org.springframework.boot‹/groupId›
    ‹artifactId›spring-boot-starter-aop‹/artifactId›
‹/dependency›
</pre>
@Aspect 역할을 하는 클래스를 만들어준다.
<pre>
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
    /**
     * @Around 에는 포인트컷 이름을 주거나 포인트컷을 직접 정의할 수 있다.
     * @Around(...) => logPert() 어드바이스를 어떻게 적용할 것인가.
     * @Around 는 메서드(여기에서는 createEvent(), publishEvent())를 감싼 형태로 적용됨.
     * 때문에 메서드 호출 이전에도 뭔가를 할 수 있고, 이후에도 뭔가를 할 수 있다.
     * 또는 메소드에서 발생한 에러를 잡아서 에러가 발생했을 때 특정한 일을 할수도 있다.
     */
    @Around("execution(* sombrero.aop.spring_aop.EventService.*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = pjp.proceed(); // 메서드 호출
        System.out.println("# [aop][spring_aop][PerfAspect] time: " + (System.currentTimeMillis() - begin));
        return retVal;
    }

}
</pre>
어플리케이션을 실행하면 AOP가 적용된 것을 확인할 수 있다.<br/>
기존의 클래스를 건드리지 않고도 여러 클래스에 중복 코드없이 AOP가 적용되는 것을 확인할 수 있다.<br/>
(인텔리제이 얼티메이트(유료버전)버전에서는 Aspect가 어디에 적용되는지 IDE에서 메서드에 표시된다.)<br/>
<br/>
하지만 여기에서 deleteEvent() 메소드는 AOP가 적용되지 않길 원했는데 이 메소드까지도 AOP가 적용되는 것을 확인할 수 있다.<br/>
deleteEvent() 메소드를 AOP에서 제외하고, createEvent(), publishEvent() 메소드만 AOP를 적용해보자.<br/>
<br/>
## * 커스텀 AOP 애노테이션 만들기 (선택적 메소드)
아래와 같이 애노테이션을 만들어준다.<br/>
<pre>
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Documented
public @interface PerfLogging {
}
</pre>
AOP를 적용할 메소드에 위에서 만든 애노테이션을 붙여준다.<br/>
<pre>
@PerfLogging
@Override
public void createEvent() {
    ...
}

@PerfLogging
@Override
public void publishEvent() {
    ...
}
</pre>
Aspect 클래스에 어드바이스를 적용할 애노테이션으로 위에서 만든 애노테이션 이름을 설정한다.<br/>
<pre>
@Component
@Aspect
public class PerfAspect2 {
    @Around("@annotation(PerfLogging)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        ...
    }
}
</pre>
어플리케이션을 실행하면 위에서 만든 애노테이션이 붙은 메소드만 AOP가 적용되는 것을 확인할 수 있다.<br/>
<br/>

### ** 또는 빈 이름으로 AOP를 설정할 수도 있다. (모든 메소드)
아래처럼 설정하면 'simpleEventService3' 빈의 메소드들 모두 AOP를 적용해준다. <br/>
<pre>
@Component
@Aspect
public class PerfAspect2 {
    @Around("bean(simpleEventService3)")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        ...
    }
}
</pre>
=> sombrero.aop.spring_aop_annotation 패키지 참조.<br/>

<br/><br/><br/><br/>

# 스프링 데이터 JPA의 트랜잭션 AOP가 하는 일
<pre>
public interface PetRepository extends Repository❮Pet, Integer❯ {
    @Query("SELECT ptype FROM PetType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List❮PetType❯ findPetTypes();

    @Transactional(readOnly = true)
    Pet findById(Integer id);

    // @Transactional 애노테이션이 생략되어 있을 뿐, 트랜잭션은 적용된다. 
    void save(Pet pet);
}
</pre>
- 트랜잭션 매니저를 가지고 auto commit을 false로 만든다.
- 작업이 끝나면 트랜잭션을 commit 한다. 
- 작업은 try/catch로 묶여져 있다. 
- catch 블록 안에서 어떠한 문제가 생겼을 때, 트랜잭션을 롤백 시킨다. 
- 스프링 데이터 JPA가 제공하는 메소드에는 모두 기본적으로 트랜잭션이 적용된다. 

<br/><br/><br/><br/>

# Null-safety
스프링 프레임워크 5에 추가된 Null 관련 애노테이션<br/>
목적: (툴의 지원을 받아서) 컴파일 시점에 최대한 NullPointException을 방지하는 것.<br/>
null이 아니어야 하는 곳에 사용. null이 들어가게 되면 IDE에서 컴파일 에러가 발생해서 확인 가능. <br/>
- @NonNull: 무조건 값이 있어야 할 경우 사용. 값을 넣지 않으면 IDE에서 컴파일 에러 발생.
- @Nullable
- @NonNullApi(패키지 레벨 설정): 이 패키지 이하 모든 리턴타입, 파라미터 모두 @NonNull을 적용.(package-info.java)
- @NonNullFields(패키지 레벨 설정)

IntelliJ에서 'Proferences > Compiler > Configure annotations'에 스프링 애노테이션을 추가한 후 재시작해줘야 한다. <br/>
정확히는 컴파일 에러가 아닌 Warning 메세지가 뜬다. <br/>
=> sombrero.null_safety 패키지 참조. <br/>

<br/><br/>