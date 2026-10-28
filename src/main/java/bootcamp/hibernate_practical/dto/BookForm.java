package bootcamp.hibernate_practical.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookForm {
    private String title;
    private String author;
    private String genre;
    private Integer publicationYear;
}
