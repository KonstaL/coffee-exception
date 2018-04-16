package fi.tinsta.coffee_exception.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends AbstractPersistable<Long> {


    @Column(nullable = false, unique = true)
    private String userName;

    private String imageUrl;

    @OneToMany(mappedBy = "user")
    private List<BlogPost> blogPosts;

    public User(String userName) {
        this.userName = userName;
    }

    public User() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
