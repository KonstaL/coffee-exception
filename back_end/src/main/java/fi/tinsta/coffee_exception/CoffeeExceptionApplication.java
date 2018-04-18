package fi.tinsta.coffee_exception;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableEntityLinks
@EnableTransactionManagement
public class CoffeeExceptionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeExceptionApplication.class, args);
    }

}
