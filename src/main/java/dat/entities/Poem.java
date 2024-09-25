package dat.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Purpose:
 *
 * @author: Jeppe Koch
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "poem")
public class Poem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "poem", length = 1000)
    private String poem;
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;


    public Poem(String poem, String title, String author, Type type) {
        this.poem = poem;
        this.title = title;
        this.author = author;
        this.type = type;
    }
}
