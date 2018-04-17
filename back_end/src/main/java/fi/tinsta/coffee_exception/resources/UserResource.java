package fi.tinsta.coffee_exception.resources;


import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Relation(value="author", collectionRelation="authors")
public class UserResource extends ResourceWithEmbeddeds {

    private final String username;
    private final String imageUrl;

    @JsonCreator
    public UserResource(@JsonProperty("username") String username, @JsonProperty("imageUrl") String imageUrl) {
        super();
        this.imageUrl = imageUrl;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
