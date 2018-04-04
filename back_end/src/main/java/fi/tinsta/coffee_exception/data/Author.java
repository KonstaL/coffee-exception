package fi.tinsta.coffee_exception.data;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String userName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<BlogPost> blogPosts;

    public Author(String userName) {
        this.userName = userName;
    }

    public Author() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }
}
