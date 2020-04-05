# 스프링 핵심기술 (3편)
<br/>

# 스프링 AOP
Aspect-oriented Programming(AOP)는 OOP를 보완하는 수단으로, <br/>
흩어진 Aspect를 모듈화 할 수 있는 프로그래밍 기법. <br/>
<br/>

![aop](./images/aop.png)<br/>

## AOP 주요 개념
- Aspect(모듈)와 Target(적용 대상 클래스)<br/>
- Advice(해야할 일들)<br/>
- Join point<br/>
합류점, 끼어들 수 있는 지점.<br/>
메서드 실행시점을 흔하게 사용. (메서드 실행할 때 Advice를 끼워넣어라.)<br/>
생성자 호출 직전. 생성자 호출 했을 때.<br/>
필드에 접근하기 전. 필드에서 값을 가져갔을 때.<br/>
- Pointcut(어디에 적용)<br/>

## AOP 적용 방법
- 컴파일 타임<br/>
- 로드 타임<br/>
- 런 타임<br/>

<br/><br/>

