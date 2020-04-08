package sombrero.null_safety;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @NonNull // 리턴값이 null이면 안될 경우. => @NonNull 사용.
    public String createEvent(@NonNull String name) { // 인자값 name이 무조건 값이 있어야 할 때. => @NonNull 사용.
        // return null;
        return "hello " + name;
    }

}
