//package fi.tinsta.coffee_exception.resources.assembler;
//
//
//
////import static com.opencredo.demo.hateoas.api.ResourceIdFactory.getId;
//
//import fi.tinsta.coffee_exception.controller.UserController;
//import fi.tinsta.coffee_exception.data.User;
//import fi.tinsta.coffee_exception.data.BlogPost;
//import fi.tinsta.coffee_exception.resources.UserResource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.EntityLinks;
//import org.springframework.hateoas.Link;
//import org.springframework.hateoas.RelProvider;
//import org.springframework.stereotype.Service;
//
//
//
//@Service
//public class UserResourceAssembler extends EmbeddableResourceAssemblerSupport<User, UserResource, UserController>{
//
//    @Autowired
//    private BlogPostResourceAssembler blogPostResourceAssembler;
//
//    @Autowired
//    public UserResourceAssembler(final EntityLinks entityLinks, final RelProvider relProvider) {
//        super(entityLinks, relProvider, UserController.class, UserResource.class);
//    }
//
//    @Override
//    public Link linkToSingleResource(User user) {
//        return entityLinks.linkToSingleResource(UserResource.class, user.getId());
//    }
//
//    @Override
//    public UserResource toResource(User entity) {
//        final UserResource resource = createResourceWithId(entity.getId(), entity);
//        // Add (multiple) links to authored books
//        for(BlogPost post : entity.getBlogPosts()) {
//            resource.add( blogPostResourceAssembler.linkToSingleResource(post).withRel("authored-posts") );
//        }
//
//        return resource;
//    }
//
//    @Override
//    protected UserResource instantiateResource(User entity) {
//        return new UserResource(entity.getUsername(), entity.getImageUrl());
//    }
//
//}
//
