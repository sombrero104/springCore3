package sombrero.aop.proxy_pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * AOP에서 Proxy 역할.
 */
/**
 * @Primary
 * 같은 타입의 빈이 여러개가 있을 때 그 중 하나를 선택하는 방법.
 */
@Primary
@Service
public class ProxySimpleEventService implements EventService {

    /**
     * 빈의 이름으로 기반해서 주입받기 때문에
     * simpleEventService 빈을 EventService 타입으로 받아도 상관없음.
     */
    /*@Autowired
    EventService simpleEventService;*/

    @Autowired
    SimpleEventService simpleEventService;

    @Override
    public void createEvent() {
        long begin = System.currentTimeMillis();
        simpleEventService.createEvent();
        System.out.println("# [aop][proxy_pattern][ProxySimpleEventService] createEvent time: " + (System.currentTimeMillis() - begin));
    }

    @Override
    public void publishEvent() {
        long begin = System.currentTimeMillis();
        simpleEventService.publishEvent();
        System.out.println("# [aop][proxy_pattern][ProxySimpleEventService] publishEvent time: " + (System.currentTimeMillis() - begin));
    }

    @Override
    public void deleteEvent() {
        simpleEventService.deleteEvent();
    }

}
