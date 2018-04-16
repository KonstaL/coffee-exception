package fi.tinsta.coffee_exception.controller;

import fi.tinsta.coffee_exception.resources.assembler.BlogPostResourceAssembler;
import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.data.BlogPostRepository;
import fi.tinsta.coffee_exception.resources.BlogPostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(BlogPostResource.class) // This is required to have EntityLinks working
@RequestMapping("/posts")
@Transactional // Making the controller transactional is just a way to simplify the persistence implementation (out of scope for this demo)
public class BlogPostController {

    BlogPostRepository blogPostRepository;
    BlogPostResourceAssembler blogPostResourceAssembler;

    @Autowired
    public BlogPostController(BlogPostResourceAssembler blogPostResourceAssembler, BlogPostRepository blogPostRepo) {
        this.blogPostRepository = blogPostRepo;
        this. blogPostResourceAssembler = blogPostResourceAssembler;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Resources<BlogPostResource>> findAll() {
        Iterable<BlogPost> blogPosts = blogPostRepository.findAll();
        Resources<BlogPostResource> wrapped = blogPostResourceAssembler.toEmbeddedList(blogPosts);

        //        for (User author : authors) {
//            Link selfLink = linkTo(AuthorController.class).slash(author.getId()).withSelfRel();
//            author.add(selfLink);
//        }

        return new ResponseEntity<>(wrapped, HttpStatus.OK);
    }



    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BlogPost> addOne(@RequestBody BlogPost blogPost) {
        System.out.println("New post: " + blogPost.toString());

        // not sure if this works
        return new ResponseEntity<>(blogPostRepository.save(blogPost), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPostResource> getSinglePost(@PathVariable long id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            System.out.println(blogPostOptional.get().getComments());
            BlogPostResource resource = blogPostResourceAssembler.toResource(blogPostOptional.get());
            return new ResponseEntity<>(resource, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteData(@PathVariable long id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            blogPostRepository.delete(blogPostOptional.get());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> updateSinglePost(@PathVariable long id, @RequestBody BlogPost blogPost) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            BlogPost test = blogPostOptional.get();
            blogPost.setTitle("moi niko");
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
