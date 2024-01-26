package KEYSAT;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class API {

    @GetMapping("/")
    public String sayHello() {
        return "KEYSAT APP";
    }



    }
