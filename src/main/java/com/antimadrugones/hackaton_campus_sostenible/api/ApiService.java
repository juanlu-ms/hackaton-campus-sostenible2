package com.antimadrugones.hackaton_campus_sostenible.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

// Para API externa
@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    private static final String API_URL = "https://hackaton-campus-sostenible-api.mmartinez-d6a.workers.dev/containers/measurements";
    private static final String BEARER_TOKEN = "campus-sostenible-2025";

    @Autowired
    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Measurement> fetchContainerData() {
        try {
            logger.info("Consultando API: {}", API_URL);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + BEARER_TOKEN);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<List<Measurement>> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<>() {
                    }
            );

            List<Measurement> containers = response.getBody();
            logger.info("Datos obtenidos: {} contenedores", containers != null ? containers.size() : 0);

            return containers;

        } catch (Exception e) {
            logger.error("Error al consultar API: {}", e.getMessage());
            throw new RuntimeException("No se pudo obtener datos de la API", e);
        }
    }


}