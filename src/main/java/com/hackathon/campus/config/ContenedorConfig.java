package com.hackathon.campus.config;

import com.hackathon.campus.repository.ContenedorRepository;
import com.hackathon.campus.service.ApiContenedorService;
import com.hackathon.campus.service.ContenedorService;
import com.hackathon.campus.service.DbContenedorService;
import com.hackathon.campus.service.MockContenedorService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ContenedorConfig {
    @Value("${contenedor.data.source:mock}")
    private String dataSource;

    @Bean
    public ContenedorService contenedorService(
            ContenedorRepository repository,
            RestTemplate restTemplate) {

        return switch (dataSource) {
            case "api" -> new ApiContenedorService(restTemplate);
            case "db" -> new DbContenedorService(repository);
            default -> new MockContenedorService();
        };
    }
}
