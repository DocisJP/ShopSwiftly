package c15_23_m_java_react.com.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    
    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
