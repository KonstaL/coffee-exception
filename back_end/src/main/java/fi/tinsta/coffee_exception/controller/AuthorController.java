
package fi.tinsta.coffee_exception.controller;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import fi.tinsta.coffee_exception.AuthorResourceAssembler;
import fi.tinsta.coffee_exception.data.Author;
import fi.tinsta.coffee_exception.data.AuthorRepository;
import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.data.BlogPostRepository;
import fi.tinsta.coffee_exception.resources.AuthorResource;
import fi.tinsta.coffee_exception.resources.BlogPostResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@ExposesResourceFor(AuthorResource.class) // This is required to have EntityLinks working
@RequestMapping("/authors")
@Transactional// Making the controller transactional is just a way to simplify the persistence implementation (out of scope for this demo)
public class AuthorController {

    AuthorRepository authorRepository;
    AuthorResourceAssembler authorResourceAssembler;

    @Autowired
    public AuthorController(AuthorRepository authorRepo, AuthorResourceAssembler authorResourceAssembler) {
        this.authorResourceAssembler = authorResourceAssembler;
        this. authorRepository = authorRepo;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Resources<AuthorResource>> findAllAuthors() {
        Iterable<Author> authors = authorRepository.findAll();
        System.out.println("====================================================");
        System.out.println(authors);
        final Resources<AuthorResource> wrapped = authorResourceAssembler.toEmbeddedList(authors);
//        for (Author author : authors) {
//            Link selfLink = linkTo(AuthorController.class).slash(author.getId()).withSelfRel();
//            author.add(selfLink);
//        }
        return new ResponseEntity<>(wrapped, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AuthorResource> getSingleAuthor(@PathVariable long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if(authorOptional.isPresent()) {
            AuthorResource resource = authorResourceAssembler.toResource(authorOptional.get());
            return ResponseEntity.ok(resource);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
