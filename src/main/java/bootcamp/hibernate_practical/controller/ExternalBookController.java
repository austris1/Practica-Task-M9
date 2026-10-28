package bootcamp.hibernate_practical.controller;


import bootcamp.hibernate_practical.client.BookRestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

@RestController
@RequestMapping("/api/external-books")
public class ExternalBookController {

    private final BookRestClient bookRestClient;

    public ExternalBookController(BookRestClient bookRestClient) {
        this.bookRestClient = bookRestClient;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<String> getBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookRestClient.getBook(id));
        } catch (RestClientException ex) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("External API error: " + ex.getMessage());
        }
    }
}