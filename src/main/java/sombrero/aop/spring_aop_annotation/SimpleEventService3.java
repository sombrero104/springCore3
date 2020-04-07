package sombrero.aop.spring_aop_annotation;

import org.springframework.stereotype.Service;

/**
 * AOP에서 Real Subject 역할.
 *
 * [테스트 예제]
 *  이 코드를 전혀 건드리지 않고,
 *  createEvent(), publishEvent() 메소드의 시간 성능 측정하기.
 *  deleteEvent()는 제외. 시간 성능 측정하지 않음.
 */
@Service
public class SimpleEventService3 implements EventService {

    @Override
    public void createEvent() {
        // long begin = System.currentTimeMillis();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Created an event.");

        // System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void publishEvent() {
        // long begin = System.currentTimeMillis();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Published an event.");

        // System.out.println(System.currentTimeMillis() - begin);
    }

    @Override
    public void deleteEvent() {
        System.out.println("Delete an event.");
    }

}
