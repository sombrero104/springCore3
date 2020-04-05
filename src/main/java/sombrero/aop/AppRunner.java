package sombrero.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * AOP에서 Client 역할.
 */
@Component
public class AppRunner implements ApplicationRunner {

    /**
     * 주입 받을 때에는 항상
     * 인터페이스가 있을 경우 인터페이스로 주입받는 것을 권장.
     */
    @Autowired
    EventService eventService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        eventService.createEvent();
        eventService.publishEvent();
        eventService.deleteEvent();
    }

}
