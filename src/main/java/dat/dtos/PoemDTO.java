package dat.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import dat.entities.Poem;
import dat.entities.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PoemDTO {
    @JsonProperty("id")
private long id;
    @JsonProperty("title")
private String title;
    @JsonProperty("author")
private String author;
    @JsonProperty("content")
private String poem;
    @JsonProperty("type")
private Type type;

public PoemDTO(Poem poem) {
    this.title = poem.getTitle();
    this.author = poem.getAuthor();
    this.poem = poem.getPoem();
    this.type = poem.getType();
}
}
