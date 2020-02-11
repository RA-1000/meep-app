package com.assignment.demo.tools;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.function.Consumer;

import static org.springframework.web.reactive.function.client.WebClient.Builder;

public class RestClientTool<T> {
    private WebClient caller;

    public RestClientTool(String baseUrl, Consumer<HttpHeaders> headers) {
        Builder aux = WebClient.builder()
                .baseUrl(baseUrl);
        if (headers != null)
            aux.defaultHeaders(headers);

        caller = aux.build();
    }

    public List<T> get(String method, MultiValueMap<String, String> uriParams, Class<T> clazz) {
        List<T> response = null;
        ClientResponse result = caller.get()
                .uri(uriBuilder -> uriBuilder.path(method).queryParams(uriParams).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange().block();
        if (result.statusCode().is2xxSuccessful()) {
            response = result.bodyToFlux(clazz).collectList().block();
        }
        return response;
    }
}
