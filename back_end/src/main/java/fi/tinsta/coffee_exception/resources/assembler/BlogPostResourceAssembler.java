package fi.tinsta.coffee_exception.resources.assembler;

import fi.tinsta.coffee_exception.controller.BlogPostController;
import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.resources.AuthorResource;
import fi.tinsta.coffee_exception.resources.BlogPostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RelProvider;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.core.EmbeddedWrapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogPostResourceAssembler
        extends EmbeddableResourceAssemblerSupport<BlogPost, BlogPostResource, BlogPostController> {

    // Resource assemblers are not autowired in constructor as they depends each other on instantiation
    @Autowired
    private AuthorResourceAssembler authorResourceAssembler;

    @Autowired
    public BlogPostResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
        super(entityLinks, relProvider, BlogPostController.class, BlogPostResource.class);
    }

    @Override
    protected BlogPostResource instantiateResource(BlogPost entity) {
        return new BlogPostResource(entity.getId(),
                entity.getTitle(),
                entity.getBannerUrl(),
                entity.getLikes(),
                entity.getBodyItems(),
                entity.getComments());
    }

    private BlogPostResource toBaseResource(BlogPost entity) {
        final BlogPostResource resource = createResourceWithId(entity.getId(), entity);

        return resource;
    }

    @Override
    public Link linkToSingleResource(BlogPost blogPost) {
        return entityLinks.linkToSingleResource(BlogPostResource.class, blogPost.getId());
    }

    /**
     * Creates the default representation of book resource (in this case with Authors and Publishers as links)
     */
    @Override
    public BlogPostResource toResource(BlogPost entity) {
        final BlogPostResource resource = toBaseResource(entity);

        // Add links to available actions
        //addActionLinks(resource, entity);

        // Add authors as links
        final String authorRel = relProvider.getCollectionResourceRelFor(AuthorResource.class);
        //        for(Author author : entity.getAuthor()) {
        //            resource.add( authorResourceAssembler.linkToSingleResource(author).withRel(authorsRel) );
        //        }

        // resource.add(authorResourceAssembler.linkToSingleResource(entity.getAuthor()).withRel("author"));
        resource.add(authorResourceAssembler.linkToSingleResource(entity.getAuthor()).withRel(authorRel));

        return resource;
    }

    private void addActionLinks(final BlogPostResource resource, final BlogPost entity) {
        // Add "purchase" link
        //        final Link purchaseLink = linkTo(methodOn(controllerClass).purchaseBookCopies(entity.getIsbn(), null)).withRel("purchase");
        //        resource.add(purchaseLink);
        //
        //        // Conditionally add "borrow" link, if there is any copy available
        //        if ( entity.getCopiesAvailable() > 0 ) {
        //            final Link borrowLink = linkTo(methodOn(controllerClass).borrowACopy(entity.getIsbn())).withRel("borrow");
        //            resource.add(borrowLink);
        //        }
        //
        //        // Add "return" link
        //        final Link returnLink = linkTo(methodOn(controllerClass).returnACopy(entity.getIsbn()) ).withRel("return");
        //        resource.add(returnLink);
    }

    /**
     * Creates a custom, detailed representation of book resource, embedding authors and publishers
     *
     * @param entity
     */
    public BlogPostResource toDetailedResource(BlogPost entity) {
        final BlogPostResource resource = toBaseResource(entity);

        // Add links to available actions
        addActionLinks(resource, entity);

        // Create the collection of embeddables of different types
        final List<EmbeddedWrapper> embeddables = new ArrayList<EmbeddedWrapper>();
        // Add authors
        embeddables.add(authorResourceAssembler.toEmbeddable(entity.getAuthor()));

        resource.setEmbeddeds(new Resources<>(embeddables)); // Note it must be wrapped in a Resources

        return resource;
    }

}
