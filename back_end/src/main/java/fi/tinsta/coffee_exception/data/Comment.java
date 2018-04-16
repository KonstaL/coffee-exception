package fi.tinsta.coffee_exception.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@Entity
public class Comment extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    private String text;
    private int likes;
    private LocalDate localDate;

    public Comment(User user, String text) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
