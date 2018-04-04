package fi.tinsta.coffee_exception.data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class BlogPost {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private LocalDate date;

    @OneToOne(cascade = CascadeType.ALL)
    private Author author;


    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<String> bodyItems;

    public BlogPost(String title, Author author, List<String> bodyItems) {
        this.title = title;
        this.author = author;
        this.bodyItems = bodyItems;
        this.setDate(LocalDate.now());
    }

    public BlogPost() {
        this.setDate(LocalDate.now());
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getBodyItems() {
        return bodyItems;
    }

    public void setBodyItems(List<String> bodyItems) {
        this.bodyItems = bodyItems;
    }


}
