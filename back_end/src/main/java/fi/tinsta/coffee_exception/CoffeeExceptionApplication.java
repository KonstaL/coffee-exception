package fi.tinsta.coffee_exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
@EnableEntityLinks
public class CoffeeExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeExceptionApplication.class, args);
    }

}
