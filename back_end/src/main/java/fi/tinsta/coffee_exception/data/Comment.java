package fi.tinsta.coffee_exception.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class Comment extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    @JsonBackReference
    private Author author;

    private String text;
    private int likes;
    private LocalDate localDate;

    public Comment(Author author, String text) {
        this.author = author;
        this.text = text;
        this.setLocalDate(LocalDate.now());
    }

    public Comment() {
        this.setLocalDate(LocalDate.now());
    }

    public int getLikes() {
        return likes;
    }

    public void addLike() {
        this.likes++;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
