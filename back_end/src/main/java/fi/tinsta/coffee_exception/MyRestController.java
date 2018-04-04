package fi.tinsta.coffee_exception;

import fi.tinsta.coffee_exception.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController implements CommandLineRunner {

    @Autowired
    BlogPostRepository blogPostRepository;

    @RequestMapping("/")
    public String test() {
        return "Onnittelut bäkkärin toiminnasta!";
    }




    @RequestMapping("/get")
    public ResponseEntity<Iterable<BlogPost>> getData() {
        return new ResponseEntity<>(blogPostRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = "application/json", produces = "application/json; charset=UTF-8")
    public ResponseEntity<BlogPost> addDate() {
        List<String> items = new ArrayList<>();
        items.add("<h2>Some bodytext<h2>");
        BlogPost blogPost = new BlogPost("Some blogpost", new Author("JussiFani42"),items);
        return new ResponseEntity<BlogPost>(blogPostRepository.save(blogPost), HttpStatus.OK);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
