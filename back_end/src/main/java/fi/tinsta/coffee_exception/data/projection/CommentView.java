package fi.tinsta.coffee_exception.data.projection;

import fi.tinsta.coffee_exception.data.Comment;
import fi.tinsta.coffee_exception.data.User;
import org.springframework.data.rest.core.config.Projection;

import java.time.LocalDate;

//this is a subprojection to enable comments user to be shown
@Projection(types = {Comment.class})
public interface CommentView {
    User getUser();

    String getText();

    int getLikes();

    LocalDate getLocalDate();

}
