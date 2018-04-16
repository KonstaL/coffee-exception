package fi.tinsta.coffee_exception.controller;

import fi.tinsta.coffee_exception.resources.assembler.IndexResourceAssembler;
import fi.tinsta.coffee_exception.resources.IndexResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    private final IndexResourceAssembler indexResourceAssembler;


    @Autowired
    public IndexController(IndexResourceAssembler indexResourceAssembler) {
        this.indexResourceAssembler = indexResourceAssembler;
    }

//    // Serve ReactBundle
//    @RequestMapping(method= RequestMethod.GET)
//    public String serve() {
//        return "index.html";
//    }

    // Give basic information about the API
    @RequestMapping(method= RequestMethod.GET, consumes = "application/json",
            produces = "application/json; charset=UTF-8")
    public ResponseEntity<IndexResource> index() {
        return ResponseEntity.ok(indexResourceAssembler.buildIndex());
    }
}
