package fi.tinsta.coffee_exception.data;

import fi.tinsta.coffee_exception.data.projection.InlineUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "blogpost", path = "posts")
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    // List<BlogPost> findAllByDate_DayIsGreaterThan(Date date);

}
