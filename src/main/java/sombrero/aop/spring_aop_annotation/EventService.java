package sombrero.aop.spring_aop_annotation;

/**
 * AOP에서 Subject 인터페이스 역할.
 */
public interface EventService {

    void createEvent();

    void publishEvent();

    void deleteEvent();

}
