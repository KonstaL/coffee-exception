package fi.tinsta.coffee_exception;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import fi.tinsta.coffee_exception.data.Author;
import fi.tinsta.coffee_exception.data.AuthorRepository;
import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.data.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MyRestController implements CommandLineRunner {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    AuthorRepository authorRepository;

    @RequestMapping("/")
    public String htmlRoot() {
        return "Hi! Welcome to my kong strong backend!";
    }


    @RequestMapping(value = "/posts", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Iterable<BlogPost>> findAll() {
        return new ResponseEntity<>(blogPostRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BlogPost> addOne(@RequestBody BlogPost blogPost) {
        System.out.println("New post: " + blogPost.toString());

        // not sure if this works
        return new ResponseEntity<>(blogPostRepository.save(blogPost), HttpStatus.OK);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getSinglePost(@PathVariable int id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            return new ResponseEntity<>(blogPostOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteData(@PathVariable int id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            blogPostRepository.delete(blogPostOptional.get());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> updateSinglePost(@PathVariable int id, @RequestBody BlogPost blogPost) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
             BlogPost test = blogPostOptional.get();
             blogPost.setTitle("moi niko");
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "/authors", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Iterable<Author>> findAllAuthors() {
        return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
//    public ResponseEntity<Author> getSingleAuthor(@PathVariable int id) {
//        Optional<Author> authorOptional = authorRepository.findById(id);
//
//        if(authorOptional.isPresent()) {
//            return new ResponseEntity<>(authorOptional.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    //Seed the data
    @Override
    public void run(String... args) throws Exception {
        Lorem lorem = new LoremIpsum();

        for (int i = 0; i < 10; i++) {
            List<String> items = new ArrayList<>();
            for (int b = 0; b < ((int) (Math.random() * 7)); b++) {
                items.add(lorem.getParagraphs(1, 5));
            }

            Author author = authorRepository.save(new Author(lorem.getName()));
            BlogPost blogPost = new BlogPost(lorem.getTitle(1), author, items);
            blogPostRepository.save(blogPost);
        }
    }
}
