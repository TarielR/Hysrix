package tariel;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableCircuitBreaker
public class HystrixService {

    @Value("${producer.url}")
    private String URL;

    @HystrixCommand(fallbackMethod = "getDefaultAddress")
    public String getAddressFromAWS() {
        return new RestTemplate().getForObject(URL, String.class);
    }

    public String  getDefaultAddress() {
        return "Default address";
    }
}
