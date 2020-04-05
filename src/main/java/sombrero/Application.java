package sombrero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // SpringApplication.run(Application.class, args);

        /**
         * 스프링부트를 웹 애플리케이션이 아닌(서버 모드가 아닌) 상태로 띄우는 방법.
         * 보통의 main() 메소드 실행처럼..
         * 웹 서버가 기동되지 않기 때문에 좀 더 빠름.
         */
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

}
