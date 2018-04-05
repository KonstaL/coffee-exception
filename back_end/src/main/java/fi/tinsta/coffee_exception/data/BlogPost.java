package fi.tinsta.coffee_exception.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

//    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
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
