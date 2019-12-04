package online.keepon.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/**
 * @author felix
 */
@SpringBootApplication
public class OauthDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(OauthDemoApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
