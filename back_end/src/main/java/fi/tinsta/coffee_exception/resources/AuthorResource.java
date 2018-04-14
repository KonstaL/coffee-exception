package fi.tinsta.coffee_exception.resources;


import org.springframework.hateoas.core.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Relation(value="author", collectionRelation="authors")
public class AuthorResource extends ResourceWithEmbeddeds {

    private final String username;

    @JsonCreator
    public AuthorResource(@JsonProperty("username") String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
