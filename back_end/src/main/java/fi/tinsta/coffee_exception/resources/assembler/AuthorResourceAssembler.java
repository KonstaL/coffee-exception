package fi.tinsta.coffee_exception.resources.assembler;



//import static com.opencredo.demo.hateoas.api.ResourceIdFactory.getId;

import fi.tinsta.coffee_exception.controller.AuthorController;
import fi.tinsta.coffee_exception.data.Author;
import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.resources.AuthorResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Service;



@Service
public class AuthorResourceAssembler extends EmbeddableResourceAssemblerSupport<Author, AuthorResource, AuthorController>{

    @Autowired
    private BlogPostResourceAssembler blogPostResourceAssembler;

    @Autowired
    public AuthorResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, AuthorController.class, AuthorResource.class);
    }

    @Override
    public Link linkToSingleResource(Author author) {
        return entityLinks.linkToSingleResource(AuthorResource.class, author.getId());
    }

    @Override
    public AuthorResource toResource(Author entity) {
        final AuthorResource resource = createResourceWithId(entity.getId(), entity);
        // Add (multiple) links to authored books
        for(BlogPost post : entity.getBlogPosts()) {
            resource.add( blogPostResourceAssembler.linkToSingleResource(post).withRel("authored-posts") );
        }

        return resource;
    }

    @Override
    protected AuthorResource instantiateResource(Author entity) {
        return new AuthorResource(entity.getUserName());
    }

}

