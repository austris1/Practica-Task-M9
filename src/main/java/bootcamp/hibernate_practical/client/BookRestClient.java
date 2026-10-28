package bootcamp.hibernate_practical.client;

import bootcamp.hibernate_practical.config.ExternalServiceProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BookRestClient {

    private final RestClient restClient;

    public BookRestClient(RestClient.Builder builder, ExternalServiceProperties properties) {
        this.restClient = builder
                .baseUrl(properties.baseUrl())
                .defaultHeader("Authorization", "Bearer " + properties.apiToken())
                .build();
    }

    public String getBook(Long id) {
        return restClient.get()
                .uri("/books/{id}", id)
                .retrieve()
                .body(String.class);
    }
}
