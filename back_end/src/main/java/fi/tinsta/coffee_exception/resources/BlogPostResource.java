package fi.tinsta.coffee_exception.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fi.tinsta.coffee_exception.resources.ResourceWithEmbeddeds;
import org.springframework.hateoas.core.Relation;

// Resource class is NOT immutable, to simplify deserialisation using Jackson
@Relation(value = "blogpost", collectionRelation = "blogposts")
public class BlogPostResource extends ResourceWithEmbeddeds {

    //Vielä vajaa
    private final String title;

    @JsonCreator
    public BlogPostResource(@JsonProperty("title") String title) {
        super();
        this.title = title;
    }


    public String getTitle() {
        return title;
    }
}
