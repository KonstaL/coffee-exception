package fi.tinsta.coffee_exception.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "blogposts", path = "posts")
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    // List<BlogPost> findAllByDate_DayIsGreaterThan(Date date);

}
