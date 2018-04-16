package fi.tinsta.coffee_exception.resources.assembler;


import static java.util.Arrays.asList;

import java.util.List;

import fi.tinsta.coffee_exception.resources.UserResource;
import fi.tinsta.coffee_exception.resources.BlogPostResource;
import fi.tinsta.coffee_exception.resources.IndexResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Service;


@Service
public class IndexResourceAssembler {
    private final RelProvider relProvider;
    private final EntityLinks entityLinks;

    @Autowired
    public IndexResourceAssembler(RelProvider relProvider, EntityLinks entityLinks) {
        this.relProvider = relProvider;
        this.entityLinks = entityLinks;
    }

    public IndexResource buildIndex() {

        final List<Link> links = asList(
                entityLinks.linkToCollectionResource(BlogPostResource.class).withRel( relProvider.getCollectionResourceRelFor(BlogPostResource.class) ),
                entityLinks.linkToCollectionResource(UserResource.class).withRel( relProvider.getCollectionResourceRelFor(UserResource.class) )
        );
        final IndexResource resource = new IndexResource("Coffee Exception HATEAOS API", "This the backend API for the Coffee Exception blog. You can get data by authors or by posts");
        resource.add(links);
        return resource;
    }
}