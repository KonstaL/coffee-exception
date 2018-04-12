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
@RequestMapping("/posts")
@Transactional // Making the controller transactional is just a way to simplify the persistence implementation (out of scope for this demo)
public class BlogPostController implements CommandLineRunner {

    BlogPostRepository blogPostRepository;
    AuthorRepository authorRepository;

    @Autowired
    public BlogPostController(AuthorRepository authorRepo, BlogPostRepository blogPostRepo) {
        this.blogPostRepository = blogPostRepo;
        this. authorRepository = authorRepo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Iterable<BlogPost>> findAll() {
        return new ResponseEntity<>(blogPostRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BlogPost> addOne(@RequestBody BlogPost blogPost) {
        System.out.println("New post: " + blogPost.toString());

        // not sure if this works
        return new ResponseEntity<>(blogPostRepository.save(blogPost), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<BlogPost> getSinglePost(@PathVariable int id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            return new ResponseEntity<>(blogPostOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteData(@PathVariable int id) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            blogPostRepository.delete(blogPostOptional.get());
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BlogPost> updateSinglePost(@PathVariable int id, @RequestBody BlogPost blogPost) {
        Optional<BlogPost> blogPostOptional = blogPostRepository.findById(id);

        if(blogPostOptional.isPresent()) {
            BlogPost test = blogPostOptional.get();
            blogPost.setTitle("moi niko");
            return new ResponseEntity<>(blogPost, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Seed the data
    @Override
    public void run(String... args) throws Exception {
        Lorem lorem = new LoremIpsum();

        //makes 10 authors
        for (int i = 0; i < 10; i++) {
            Author author = authorRepository.save(new Author(lorem.getName()));

            //Make 1-5 blogposts
            for (int j = 0; j < 4; j++) {
                List<String> items = new ArrayList<>();

                //add 1-7 paragraps to the post
                for (int b = 0; b < 3; b++) {
                    items.add(lorem.getHtmlParagraphs(1,1));
                }

                BlogPost blogPost = new BlogPost(lorem.getTitle(1), author, items);
                blogPostRepository.save(blogPost);
            }
        }
    }
}
