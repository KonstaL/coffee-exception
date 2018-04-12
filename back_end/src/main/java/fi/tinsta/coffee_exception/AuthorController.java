
package fi.tinsta.coffee_exception;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import fi.tinsta.coffee_exception.data.Author;
import fi.tinsta.coffee_exception.data.AuthorRepository;
import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.data.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
//@ExposesResourceFor(BookResource.class) // This is required to have EntityLinks working
@RequestMapping("/authors")
@Transactional
// Making the controller transactional is just a way to simplify the persistence implementation (out of scope for this demo)
public class AuthorController {

    BlogPostRepository blogPostRepository;
    AuthorRepository authorRepository;

    @Autowired
    public AuthorController(AuthorRepository authorRepo, BlogPostRepository blogPostRepo) {
        this.blogPostRepository = blogPostRepo;
        this. authorRepository = authorRepo;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Iterable<Author>> findAllAuthors() {
        Iterable<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            Link selfLink = linkTo(AuthorController.class).slash(author.getAuthorID()).withSelfRel();
            author.add(selfLink);
        }
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Author> getSingleAuthor(@PathVariable int id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if(authorOptional.isPresent()) {
            return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
