package fi.tinsta.coffee_exception.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class BlogPost extends AbstractPersistable<Long> {


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    //@JoinTable(name = "books_author", joinColumns = {@JoinColumn(name="blogpost_id", referencedColumnName = "id")},
    //                            inverseJoinColumns = {@JoinColumn(name="author_id", referencedColumnName = "id")})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
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
