package fi.tinsta.coffee_exception.data;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BlogPost extends AbstractPersistable<Long> {


    @Column(nullable = false)
    private String title;

    private int likes;

    @Column(nullable = false)
    private String bannerUrl;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    private List<Comment> comments;

    @ElementCollection
    @Column(columnDefinition = "TEXT")
    private List<String> bodyItems;

    public BlogPost() {
        this.setDate(LocalDate.now());
    }

    public BlogPost(String title, Author author, String bannerUrl, List<String> bodyItems) {
        this.title = title;
        this.author = author;
        this.bannerUrl = bannerUrl;
        this.bodyItems = bodyItems;
        this.comments = new ArrayList<>();
        this.setDate(LocalDate.now());
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComments(Comment... comments) {
        for (Comment comment : comments) {
            this.comments.add(comment);
        }
    }


    public String getTitle() {
        return title;
    }

    public int getLikes() {
        return likes;
    }

    public void addLike() {
        this.likes++;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
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
