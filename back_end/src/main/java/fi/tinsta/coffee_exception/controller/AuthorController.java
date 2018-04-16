package fi.tinsta.coffee_exception.controller;

import fi.tinsta.coffee_exception.resources.assembler.AuthorResourceAssembler;
import fi.tinsta.coffee_exception.data.Author;
import fi.tinsta.coffee_exception.data.AuthorRepository;
import fi.tinsta.coffee_exception.resources.AuthorResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@ExposesResourceFor(AuthorResource.class) // This is required to have EntityLinks working
@RequestMapping("/authors")
@Transactional
public class AuthorController {

    AuthorRepository authorRepository;
    AuthorResourceAssembler authorResourceAssembler;

    @Autowired
    public AuthorController(AuthorRepository authorRepo, AuthorResourceAssembler authorResourceAssembler) {
        this.authorResourceAssembler = authorResourceAssembler;
        this.authorRepository = authorRepo;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Resources<AuthorResource>> findAllAuthors() {
        Iterable<Author> authors = authorRepository.findAll();

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

        if (authorOptional.isPresent()) {
            AuthorResource resource = authorResourceAssembler.toResource(authorOptional.get());
            return ResponseEntity.ok(resource);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
