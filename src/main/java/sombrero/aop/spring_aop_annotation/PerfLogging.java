package sombrero.aop.spring_aop_annotation;

import java.lang.annotation.*;

/**
 * 이 애노테이션을 사용하면 성능을 로깅해 줍니다.
 */

/**
 * RetentionPolicy를 CLASS 이상의 값으로 설정. (기본값이 CLASS.)
 * RetentionPolicy: 이 애노테이션 정보를 얼마나 유지할 것인가.
 *   CLASS는 클래스 파일까지 유지하겠다는 의미.
 *   즉 컴파일->바이트코드->클래스 파일 안에도 이 애노테이션 정보가 남아있다는 의미.
 *   SOURCE로 설정 시 컴파일하면 애노테이션이 사라짐. 그리고 굳이 RUNTIME까지 유지할 필요는 없음.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@Documented
public @interface PerfLogging {
}
