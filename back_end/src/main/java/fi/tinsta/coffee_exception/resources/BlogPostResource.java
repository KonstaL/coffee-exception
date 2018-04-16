package fi.tinsta.coffee_exception.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.tinsta.coffee_exception.data.Comment;
import fi.tinsta.coffee_exception.resources.ResourceWithEmbeddeds;
import org.springframework.hateoas.core.Relation;

import java.util.List;

// Resource class is NOT immutable, to simplify deserialisation using Jackson
@Relation(value = "blogpost", collectionRelation = "blogposts")
public class BlogPostResource extends ResourceWithEmbeddeds {

    private final String title;
    private final List<String> bodyItems;
    private final List<Comment> comments;

    private final String test = "moi";

    private final Long postId;

    @JsonCreator
    public BlogPostResource(@JsonProperty("id") long id, @JsonProperty("title") String title,
            @JsonProperty("bodyitems") List<String> bodyItems, @JsonProperty("comments") List<Comment> comments) {
        super();
        this.postId = id;
        this.title = title;
        this.bodyItems = bodyItems;
        this.comments = comments;
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

    public String getTest() {
        return test;
    }

    public Long getPostId() {
        return postId;
    }
}
