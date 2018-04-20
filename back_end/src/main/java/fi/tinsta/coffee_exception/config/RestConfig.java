package fi.tinsta.coffee_exception.config;

import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.data.Comment;
import fi.tinsta.coffee_exception.data.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import java.net.URI;

@Configuration
public class RestConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(BlogPost.class, User.class, Comment.class);
        config.setBasePath("/api");
    }

}
