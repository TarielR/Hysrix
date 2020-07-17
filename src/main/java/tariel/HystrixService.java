package tariel;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@SpringBootApplication
@EnableCircuitBreaker
public class HystrixService {

    @Value("${producer.url}")
    private String URL;

    @HystrixCommand(fallbackMethod = "getDefaultAddress")
    public String getAddressFromAWS() {
        var text = new RestTemplate().getForObject(URL, String.class);
        return text;
    }

    public String  getDefaultAddress() {
        return "Default address";
    }

    public static void main(String[] args) {
        SpringApplication.run(HystrixService.class, args);
    }
}
