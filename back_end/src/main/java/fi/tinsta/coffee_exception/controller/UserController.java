package fi.tinsta.coffee_exception.controller;

import fi.tinsta.coffee_exception.resources.assembler.UserResourceAssembler;
import fi.tinsta.coffee_exception.data.User;
import fi.tinsta.coffee_exception.data.UserRepository;
import fi.tinsta.coffee_exception.resources.UserResource;
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
@ExposesResourceFor(UserResource.class) // This is required to have EntityLinks working
@RequestMapping("api/users")
@Transactional
public class UserController {

    UserRepository userRepository;
    UserResourceAssembler userResourceAssembler;

    @Autowired
    public UserController(UserRepository authorRepo, UserResourceAssembler userResourceAssembler) {
        this.userResourceAssembler = userResourceAssembler;
        this.userRepository = authorRepo;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Resources<UserResource>> findAllAuthors() {
        Iterable<User> authors = userRepository.findAll();

        final Resources<UserResource> wrapped = userResourceAssembler.toEmbeddedList(authors);
//        for (User author : authors) {
//            Link selfLink = linkTo(AuthorController.class).slash(author.getId()).withSelfRel();
//            author.add(selfLink);
//        }
        return new ResponseEntity<>(wrapped, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getSingleAuthor(@PathVariable long id) {
        Optional<User> authorOptional = userRepository.findById(id);

        if (authorOptional.isPresent()) {
            UserResource resource = userResourceAssembler.toResource(authorOptional.get());
            return ResponseEntity.ok(resource);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
