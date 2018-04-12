package fi.tinsta.coffee_exception;


import static java.util.Arrays.asList;

import java.util.List;

import fi.tinsta.coffee_exception.resources.AuthorResource;
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
        // Note this is unfortunately hand-written. If you add a new entity, have to manually add a new link
        final List<Link> links = asList(
                entityLinks.linkToCollectionResource(BlogPostResource.class).withRel( relProvider.getCollectionResourceRelFor(BlogPostResource.class) ),
                entityLinks.linkToCollectionResource(AuthorResource.class).withRel( relProvider.getCollectionResourceRelFor(AuthorResource.class) )
        );
        final IndexResource resource = new IndexResource("sample-hateoas", "A sample HATEOAS API");
        resource.add(links);
        return resource;
    }
}