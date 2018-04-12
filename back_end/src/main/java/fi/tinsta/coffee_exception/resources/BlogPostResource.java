package fi.tinsta.coffee_exception.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.tinsta.coffee_exception.resources.ResourceWithEmbeddeds;
import org.springframework.hateoas.core.Relation;

import java.util.List;

// Resource class is NOT immutable, to simplify deserialisation using Jackson
@Relation(value = "blogpost", collectionRelation = "blogposts")
public class BlogPostResource extends ResourceWithEmbeddeds {


    private final String title;

    private final List<String> bodyItems;

    @JsonCreator
    public BlogPostResource(@JsonProperty("title") String title, @JsonProperty("bodyitems") List<String>bodyItems ) {
        super();
        this.title = title;
        this.bodyItems = bodyItems;
    }


    public String getTitle() {
        return title;
    }

    public List<String> getBodyItems() {
        return bodyItems;
    }
}
