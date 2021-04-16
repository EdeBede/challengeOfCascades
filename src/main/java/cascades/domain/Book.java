package cascades.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @ManyToMany
    @JoinTable(
            name = "BookAuthor",
            joinColumns = {@JoinColumn(name = "bookId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authorId", referencedColumnName = "id")})

    private List<Author> authors = new ArrayList<Author>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
