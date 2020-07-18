package tariel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class HystrixController {

    private final HystrixService service;

    public HystrixController(HystrixService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String useService() {
        return service.getAddressFromAWS();
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixController.class, args);
    }
}
