package fi.tinsta.coffee_exception.data.projection;

import fi.tinsta.coffee_exception.data.BlogPost;
import fi.tinsta.coffee_exception.data.Comment;
import fi.tinsta.coffee_exception.data.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;
import java.util.List;

@Projection(name = "inline-user", types = {BlogPost.class})
public interface InlineUser {

    User getUser();

    int getLikes();

    Long getId();

    String getBannerUrl();

    List<Comment> getComments();

    List<String> getBodyItems();

    LocalDate getDate();

}



