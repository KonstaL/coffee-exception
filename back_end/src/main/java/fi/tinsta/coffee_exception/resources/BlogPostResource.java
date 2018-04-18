package fi.tinsta.coffee_exception.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.tinsta.coffee_exception.data.Comment;
import fi.tinsta.coffee_exception.resources.ResourceWithEmbeddeds;
import org.springframework.hateoas.core.Relation;

import java.time.LocalDate;
import java.util.List;

// Resource class is NOT immutable, to simplify deserialisation using Jackson
@Relation(value = "blogpost", collectionRelation = "blogposts")
public class BlogPostResource extends ResourceWithEmbeddeds {

    private final String title;
    private final int likes;
    private final List<String> bodyItems;
    private final List<Comment> comments;
    private final String bannerUrl;
    private final String username;
    private final LocalDate date;
    private final Long postId;

    @JsonCreator
    public BlogPostResource(@JsonProperty("id") long id, @JsonProperty("title") String title,
            @JsonProperty("bannerurl") String bannerUrl, @JsonProperty("likes") int likes,
            @JsonProperty("bodyitems") List<String> bodyItems, @JsonProperty("comments") List<Comment> comments,
            @JsonProperty("username") String username, @JsonProperty("date") LocalDate date) {
        super();
        this.postId = id;
        this.likes = likes;
        this.title = title;
        this.bannerUrl = bannerUrl;
        this.bodyItems = bodyItems;
        this.comments = comments;
        this.username = username;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getBodyItems() {
        return bodyItems;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Long getPostId() {
        return postId;
    }

    public int getLikes() {
        return likes;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public String getUsername() {
        return this.username;
    }

    public LocalDate getDate() {
        return this.date;
    }
}
